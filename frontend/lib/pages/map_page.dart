import 'package:flutter/material.dart';
import 'package:flutter_map/flutter_map.dart';
import 'package:frontend/models/device_location.model.dart';
import 'package:frontend/services/stomp_socket_service.dart';
import 'package:latlong2/latlong.dart';

class MapPage extends StatefulWidget {
  const MapPage({super.key});

  @override
  _MapPageState createState() => _MapPageState();
}

class _MapPageState extends State<MapPage> {
  late StompSocketService stompService;
  List<DeviceLocation> locations = [];

  @override
  void initState() {
    super.initState();
    stompService = StompSocketService(
      destination: '/topic/locations',
      onMessage: (json) {
        setState(() {
          locations.add(DeviceLocation.fromJson(json));
        });
      },
    );
    stompService.connect();
  }

  @override
  void dispose() {
    stompService.disconnect();
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: Text('Map Screen')),
      body:
          locations.isEmpty
              ? Center(child: Text('No locations available'))
              : FlutterMap(
                options: MapOptions(
                  initialCenter: LatLng(45.239608, 19.822706),
                  initialZoom: 10,
                ),
                children: [
                  TileLayer(
                    urlTemplate:
                        'https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png',
                    subdomains: ['a', 'b', 'c'],
                  ),
                  MarkerLayer(
                    markers:
                        locations.map((loc) {
                          return Marker(
                            point: LatLng(loc.latitude, loc.longitude),
                            width: 50,
                            height: 50,
                            child: Icon(
                              Icons.location_pin,
                              color: Colors.red,
                              size: 30,
                            ),
                          );
                        }).toList(),
                  ),
                ],
              ),
    );
  }
}
