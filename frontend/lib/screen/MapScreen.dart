import 'package:flutter/material.dart';
import 'package:flutter_map/flutter_map.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:frontend/model/Group.model.dart';
import 'package:frontend/providers/MapProvider.dart';
import 'package:latlong2/latlong.dart';

class MapScreen extends ConsumerWidget {
  final String groupId;

  const MapScreen({required this.groupId});

  List<Marker> getMarkers(Group group) {
    return group.locations.map((location) {
      print(location);
      return Marker(
        point: LatLng(location.lat, location.lon),
        child: Icon(Icons.location_on, color: Colors.red),
      );
    }).toList();
  }

  @override
  Widget build(BuildContext context, WidgetRef ref) {
    final groupData = ref.watch(mapDataProvider(groupId));

    return Scaffold(
      appBar: AppBar(title: Text("Map Screen")),
      body: groupData.when(
        data: (group) {
          print(group);
          return FlutterMap(
            options: MapOptions(
              initialCenter: LatLng(
                group.locations[0].lat,
                group.locations[0].lon,
              ),
              initialZoom: 10,
            ),
            children: [
              TileLayer(
                urlTemplate:
                    "https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png",
              ),
              MarkerLayer(markers: getMarkers(group)),
            ],
          );
        },
        loading: () => Center(child: CircularProgressIndicator()),
        error: (err, stack) => Center(child: Text("Error loading data: $err")),
      ),
    );
  }
}
