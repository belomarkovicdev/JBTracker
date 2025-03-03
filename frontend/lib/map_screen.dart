import 'dart:async';

import 'package:flutter/material.dart';
import 'package:flutter_map/flutter_map.dart';
import 'package:latlong2/latlong.dart';
import 'package:frontend/services/location_services.dart';

class MapScreen extends StatefulWidget {
  @override
  _MapScreenState createState() => _MapScreenState();
}

class _MapScreenState extends State<MapScreen> {
  // Fiksne koordinate (dok ne preuzimamo stvarne sa backend-a)
  double latitude = 44.8176; // Fiksna latitude (npr. Beograd)
  double longitude = 20.4633; // Fiksna longitude (npr. Beograd

  final LocationService _locationService = LocationService();
  Timer? _timer; // Timer za osvežavanje lokacije

  @override
  void initState() {
    super.initState();
    _fetchLocation();
    _startLocationRefresh(); // Početak osvežavanja lokacije svakih 10 sekundi
  }

  // Funkcija za preuzimanje lokacije
  Future<void> _fetchLocation() async {
    try {
      final location = await _locationService.fetchLocation();
      setState(() {
        latitude = location['latitude'];
        longitude = location['longitude'];
      });
    } catch (e) {
      // Ako dođe do greške prilikom preuzimanja
      ScaffoldMessenger.of(
        context,
      ).showSnackBar(SnackBar(content: Text('Failed to load location')));
    }
  }

  // Funkcija za osvežavanje lokacije svakih 10 sekundi
  void _startLocationRefresh() {
    _timer = Timer.periodic(Duration(seconds: 10), (timer) {
      _fetchLocation(); // Pozovi funkciju za preuzimanje novih koordinata
    });
  }

  @override
  void dispose() {
    _timer?.cancel(); // Zaustavite Timer kada se ekran uništi
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: Text('Location Map')),
      body:
          latitude == null || longitude == null
              ? Center(child: CircularProgressIndicator())
              : FlutterMap(
                options: MapOptions(
                  initialCenter: LatLng(latitude!, longitude!),
                  initialZoom: 13.0,
                ),
                children: [
                  TileLayer(
                    urlTemplate:
                        "https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png",
                    subdomains: ['a', 'b', 'c'],
                  ),
                  MarkerLayer(
                    markers: [
                      Marker(
                        point: LatLng(latitude!, longitude!),
                        width: 40.0,
                        height: 40.0,
                        child: Icon(
                          Icons.location_on,
                          size: 40.0,
                          color: Colors.red,
                        ),
                      ),
                    ],
                  ),
                ],
              ),
    );
  }
}
