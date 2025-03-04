import 'dart:convert';

import 'package:flutter/material.dart';
import 'package:flutter_map/flutter_map.dart';
import 'package:frontend/model/device_location.model.dart';
import 'package:frontend/model/location_data.model.dart';
import 'package:latlong2/latlong.dart';
import 'package:stomp_dart_client/stomp_dart_client.dart';

void main() {
  runApp(MyApp());
}

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Flutter WebSocket & Map',
      theme: ThemeData(primarySwatch: Colors.blue),
      home: MapScreen(),
    );
  }
}

class MapScreen extends StatefulWidget {
  @override
  _MapScreenState createState() => _MapScreenState();
}

class _MapScreenState extends State<MapScreen> {
  late StompClient stompClient;
  late MapController mapController;
  LatLng currentLocation = LatLng(0.0, 0.0); // Default location
  Map<String, DeviceLocation> deviceLocations = {};

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
    print("Connected to WebSocket!");
    stompClient.subscribe(
      destination: "/topic/location", // Subscribe to location updates
      callback: (frame) {
        print("Received location: ${frame.body}");

        // Parse the JSON string into a Dart Map]

        Map<String, dynamic> jsonMap = jsonDecode(frame.body!);

        // Convert the Map to a LocationData object
        LocationData locationData = LocationData.fromJson(jsonMap);
        // Parse latitude and longitude from WebSocket message
        setState(() {
          deviceLocations[locationData.deviceId] = DeviceLocation(
            latitude: locationData.location.x,
            longitude: locationData.location.y,
            accuracy: locationData.accuracy,
            battery: locationData.batt,
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
        options: MapOptions(initialCenter: currentLocation, initialZoom: 13.0),
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
                      color: Colors.red,
                    ),
                  );
                }).toList(),
          ),
        ],
      ),
    );
  }
}
