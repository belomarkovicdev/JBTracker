import 'dart:convert';
import 'package:flutter/material.dart';
import 'package:flutter_map/flutter_map.dart';
import 'package:frontend/model/device_location.model.dart';
import 'package:frontend/model/location.model.dart';
import 'package:frontend/model/location_data.model.dart';
import 'package:latlong2/latlong.dart';
import 'package:stomp_dart_client/stomp_dart_client.dart';

class MapScreen extends StatefulWidget {
  const MapScreen({super.key});

  @override
  _MapScreenState createState() => _MapScreenState();
}

class _MapScreenState extends State<MapScreen> {
  late StompClient stompClient;
  late MapController mapController;
  LatLng defaultLocation = LatLng(0.0, 0.0); // Default location
  Map<String, DeviceLocation> deviceLocations = {};
  Map<String, List<LatLng>> lastPositions = {};

  void saveLastPosition(String deviceId, Location newLocation) {
    if (!lastPositions.containsKey(deviceId)) {
      lastPositions[deviceId] = [];
    }
    lastPositions[deviceId]!.add(LatLng(newLocation.x, newLocation.y));
    // Keep only the last 10 positions
    if (lastPositions[deviceId]!.length > 10) {
      lastPositions[deviceId]!.removeAt(0);
    }
  }

  @override
  void initState() {
    super.initState();
    mapController = MapController();
    connectToWebSocket();
  }

  // Connect to WebSocket and subscribe to location updates
  void connectToWebSocket() {
    stompClient = StompClient(
      config: StompConfig(
        url: 'ws://localhost:8000/ws/websocket', // WebSocket URL
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
          deviceLocations[locationData.deviceId] = DeviceLocation(
            latitude: locationData.location.x,
            longitude: locationData.location.y,
            accuracy: locationData.accuracy,
            battery: locationData.batt,
          );
          saveLastPosition(
            locationData.deviceId,
            Location(x: locationData.location.x, y: locationData.location.y),
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

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: Text("Location Map")),
      body: FlutterMap(
        mapController: mapController,
        options: MapOptions(initialCenter: defaultLocation, initialZoom: 13.0),
        children: [
          TileLayer(
            urlTemplate: "https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png",
            subdomains: ['a', 'b', 'c'],
          ),
          MarkerLayer(
            markers:
                deviceLocations.entries.map((entry) {
                  final location = entry.value;
                  return Marker(
                    point: LatLng(location.latitude, location.longitude),
                    width: 80.0,
                    height: 80.0,
                    child: Icon(
                      Icons.location_on,
                      size: 40.0,
                      color: Colors.blue,
                    ),
                  );
                }).toList(),
          ),
          PolylineLayer(
            polylines:
                lastPositions.entries.map((entry) {
                  return Polyline(
                    points: entry.value,
                    color: Colors.blue,
                    strokeWidth: 3.0,
                  );
                }).toList(),
          ),
        ],
      ),
    );
  }
}
