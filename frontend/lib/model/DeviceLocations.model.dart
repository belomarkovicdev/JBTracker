import 'package:frontend/model/Location.model.dart';

class DeviceLocations {
  final String id;
  final String deviceId;
  List<Location> location;

  DeviceLocations({
    required this.id,
    required this.deviceId,
    required this.location,
  });

  factory DeviceLocations.fromJson(Map<String, dynamic> json) {
    return DeviceLocations(
      id: json['id'].toString(), // Extract deviceId from the JSON
      deviceId: json['deviceId'],
      location: Location.fromJsonList(json['locations']),
    );
  }
}
