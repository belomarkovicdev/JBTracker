import 'package:flutter/material.dart';
import 'package:flutter_map/flutter_map.dart';
import 'package:frontend/models/device_location.model.dart';
import 'package:frontend/providers/map_provider.dart';
import 'package:latlong2/latlong.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';

class MapPage extends ConsumerWidget {
  @override
  Widget build(BuildContext context, WidgetRef ref) {
    // Watch the map data provider to get the group data
    final groupData = ref.watch(mapDataProvider);

    return Scaffold(
      appBar: AppBar(title: Text("Map Screen")),
      body:
          groupData.isEmpty
              ? Center(child: Text("No locations available"))
              : FlutterMap(
                options: MapOptions(
                  initialCenter: LatLng(45.239608, 19.822706),
                  initialZoom: 10,
                ),
                children: [
                  TileLayer(
                    urlTemplate:
                        "https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png",
                    subdomains: ['a', 'b', 'c'],
                  ),
                  PolylineLayer(polylines: _getPolylines(groupData)),
                  MarkerLayer(markers: _getMarkers(groupData)),
                ],
              ),
    );
  }

  List<Polyline> _getPolylines(Map<String, List<DeviceLocation>> groupData) {
    List<Polyline> polylines = [];
    groupData.forEach((deviceId, locations) {
      final latLngList =
          locations.map((loc) => LatLng(loc.latitude, loc.longitude)).toList();
      polylines.add(
        Polyline(
          points: latLngList,
          strokeWidth: 4.0,
          color:
              Colors.primaries[int.parse(deviceId) % Colors.primaries.length],
        ),
      );
    });
    return polylines;
  }

  List<Marker> _getMarkers(Map<String, List<DeviceLocation>> groupData) {
    List<Marker> markers = [];
    groupData.entries.forEach((entry) {
      final last = entry.value.last;
      markers.add(
        Marker(
          point: LatLng(last.latitude, last.longitude),
          width: 50,
          height: 50,
          child: Icon(Icons.location_pin, color: Colors.red, size: 30),
        ),
      );
    });
    return markers;
  }
}
