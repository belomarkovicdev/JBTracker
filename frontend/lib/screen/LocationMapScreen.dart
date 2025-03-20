import 'dart:convert';
import 'package:flutter/material.dart';
import 'package:flutter_map/flutter_map.dart';
import 'package:frontend/model/DeviceLocation.model.dart';
import 'package:frontend/model/Location.model.dart';
import 'package:frontend/model/LocationData.model.dart';
import 'package:frontend/service/LocationService.dart';
import 'package:latlong2/latlong.dart';
import 'package:stomp_dart_client/stomp_dart_client.dart';

class MapScreen extends StatefulWidget {
  const MapScreen({super.key});

  @override
  _MapScreenState createState() => _MapScreenState();
}

class _MapScreenState extends State<MapScreen> {
  String tileLayer =
      "https://server.arcgisonline.com/ArcGIS/rest/services/World_Imagery/MapServer/tile/{z}/{y}/{x}";
  int maxLastLocations = 50;
  late StompClient stompClient;
  late MapController mapController;
  Map<String, DeviceLocation> deviceLocations = {};
  Map<String, List<LatLng>> lastPositions = {};
  LatLng? _currentPosition;
  final LocationService _locationService = LocationService();

  Future<void> _getCurrentLocation() async {
    LatLng? position = await _locationService.getCurrentLocation();
    if (position != null) {
      setState(() {
        _currentPosition = position;
        deviceLocations['0'] = DeviceLocation(
          latitude: position.latitude,
          longitude: position.longitude,
          accuracy: 0,
          battery: '0',
        );
        saveLastPosition(
          '0',
          Coordinates(
            x: _currentPosition!.latitude,
            y: _currentPosition!.longitude,
          ),
        );
      });
    } else {
      ScaffoldMessenger.of(
        context,
      ).showSnackBar(SnackBar(content: Text('Failed to get location.')));
    }
  }

  void saveLastPosition(String id, Coordinates newLocation) {
    if (!lastPositions.containsKey(id)) {
      lastPositions[id] = [];
    }
    lastPositions[id]!.add(LatLng(newLocation.x, newLocation.y));
    if (lastPositions[id]!.length > maxLastLocations) {
      lastPositions[id]!.removeAt(0);
    }
  }

  @override
  void initState() {
    super.initState();
    mapController = MapController();
    connectToWebSocket();
    _getCurrentLocation();
  }

  // Connect to WebSocket and subscribe to location updates
  void connectToWebSocket() {
    stompClient = StompClient(
      config: StompConfig(
        url: 'ws://192.168.1.5:8000/ws/websocket', // WebSocket URL
        onConnect: _onConnect,
        onWebSocketError: (dynamic error) => print("WebSocket Error: $error"),
      ),
    );
    stompClient.activate();
  }

  // When WebSocket is connected, subscribe to location updates
  void _onConnect(StompFrame frame) {
    stompClient.subscribe(
      destination: "/topic/location", // Subscribe to location updates
      callback: (frame) {
        // decode json to object
        Map<String, dynamic> jsonMap = jsonDecode(frame.body!);
        LocationData locationData = LocationData.fromJson(jsonMap);
        setState(() {
          deviceLocations[locationData.id] = DeviceLocation(
            latitude: locationData.coordinates.x,
            longitude: locationData.coordinates.y,
            accuracy: locationData.accuracy,
            battery: locationData.batt,
          );
          saveLastPosition(
            locationData.id,
            Coordinates(
              x: locationData.coordinates.x,
              y: locationData.coordinates.y,
            ),
          );
        });
      },
    );
  }

  @override
  void dispose() {
    stompClient.deactivate();
    super.dispose();
  }

  List<Polyline> getPolylines() {
    return lastPositions.entries.map((entry) {
      return Polyline(
        points: entry.value,
        color: Colors.blue,
        strokeWidth: 3.0,
      );
    }).toList();
  }

  List<Marker> getDeviceMarkers() {
    return deviceLocations.entries.expand((entry) {
      final positions = lastPositions[entry.key] ?? [];
      return positions.map((location) {
        return Marker(
          point: location, // Using stored positions
          width: 80.0,
          height: 80.0,
          child: Icon(Icons.location_on, size: 40.0, color: Colors.blue),
        );
      });
    }).toList();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: Text("Location Map")),
      body:
          _currentPosition == null
              ? Center(child: CircularProgressIndicator())
              : FlutterMap(
                mapController: mapController,
                options: MapOptions(
                  initialCenter: _currentPosition!,
                  initialZoom: 15.0,
                ),
                children: [
                  TileLayer(
                    urlTemplate: tileLayer,
                    subdomains: ['a', 'b', 'c'],
                  ),
                  MarkerLayer(markers: getDeviceMarkers()),
                  PolylineLayer(polylines: getPolylines()),
                ],
              ),
    );
  }
}
