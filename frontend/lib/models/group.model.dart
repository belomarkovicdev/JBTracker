import 'package:frontend/models/location_data.model.dart';

class Group {
  final String name;
  final Map<String, List<LocationData>> deviceLocations;

  Group({required this.name, required this.deviceLocations});

  factory Group.fromJson(Map<String, dynamic> json) {
    // Create a map to store deviceId as key and locations as values
    Map<String, List<LocationData>> deviceLocations = {};

    // Check if deviceTrackingSessions is available
    if (json['deviceLocations'] != null) {
      for (var session in json['deviceLocations']) {
        String deviceId =
            session['deviceId'].toString(); // Convert deviceId to string
        List<LocationData> sessionLocations = [];

        // Extract locations from each session and convert them to LocationData
        if (session['locations'] != null) {
          session['locations'].forEach((loc) {
            sessionLocations.add(LocationData.fromJson(loc));
          });
        }

        // Add the session locations to the map for the specific deviceId
        deviceLocations[deviceId] = sessionLocations;
      }
    }

    return Group(
      name: json['name'] as String,
      deviceLocations: deviceLocations,
    );
  }

  @override
  String toString() {
    // Convert the group object to a string for easy logging
    return "Group: $name, Devices: ${deviceLocations.keys.join(', ')}";
  }
}
