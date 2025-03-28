import 'package:flutter/material.dart';
import 'package:flutter_map/flutter_map.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:frontend/models/location_data.model.dart';
import 'package:frontend/providers/map_provider.dart';
import 'package:latlong2/latlong.dart';

class MapPage extends ConsumerWidget {
  const MapPage({super.key});

  // Convert a List<LocationData> to a List<LatLng>
  List<LatLng> _convertToLatLng(List<LocationData> locations) {
    return locations.map((location) {
      return LatLng(location.lat, location.lon);
    }).toList();
  }

  // Create markers for the last position of each device
  List<Marker> _createMarkers(Map<String, List<LocationData>> deviceLocations) {
    List<Marker> markers = [];

    // Iterate through device locations
    deviceLocations.forEach((deviceId, locations) {
      // Get the last location for the device
      if (locations.isNotEmpty) {
        final lastLocation = locations.last;

        // Create a marker at the last position
        markers.add(
          Marker(
            point: LatLng(lastLocation.lat, lastLocation.lon),
            width: 80.0,
            height: 80.0,
            child: Column(
              children: [
                Icon(Icons.location_on, color: Colors.red, size: 40),
                Text(
                  deviceId,
                  style: TextStyle(fontSize: 12, color: Colors.blue),
                ),
              ],
            ),
          ),
        );
      }
    });

    return markers;
  }

  @override
  Widget build(BuildContext context, WidgetRef ref) {
    // Watch the map data provider to get the group data
    final groupData = ref.watch(mapDataProvider);

    return Scaffold(
      appBar: AppBar(title: Text("Map Screen")),
      body: groupData.when(
        data: (group) {
          // Ensure group has device locations
          if (group.deviceLocations.isEmpty) {
            return Center(child: Text("No locations available"));
          }

          // Create a List of PolylineLayer options for each device
          List<Polyline> polylines = [];
          group.deviceLocations.forEach((deviceId, locations) {
            List<LatLng> latLngList = _convertToLatLng(locations);

            // Create a polyline for each device
            polylines.add(
              Polyline(
                points: latLngList,
                strokeWidth: 4.0,
                color:
                    Colors.primaries[int.parse(deviceId) %
                        Colors
                            .primaries
                            .length], // Different color for each device
              ),
            );
          });

          // Create markers for the last position of each device
          List<Marker> markers = _createMarkers(group.deviceLocations);

          return FlutterMap(
            options: MapOptions(
              initialCenter: LatLng(45.239608, 19.822706),
              initialZoom: 10,
            ),
            children: [
              TileLayer(
                urlTemplate:
                    "https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png",
              ),
              PolylineLayer(
                polylines: polylines,
              ), // Add the polylines to the map
              MarkerLayer(markers: markers), // Add the markers to the map
            ],
          );
        },
        loading: () => Center(child: CircularProgressIndicator()),
        error: (err, stack) => Center(child: Text("Error loading data: $err")),
      ),
    );
  }
}
