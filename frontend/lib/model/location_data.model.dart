import 'package:frontend/model/location.model.dart';

class LocationData {
  final String deviceId;
  final Location location;
  final double accuracy;
  final String batt;

  LocationData({
    required this.deviceId,
    required this.location,
    required this.accuracy,
    required this.batt,
  });

  factory LocationData.fromJson(Map<String, dynamic> json) {
    return LocationData(
      deviceId: json['id'].toString(), // Extract deviceId from the JSON
      location: Location.fromJson(json['location']),
      accuracy: json['accuracy'],
      batt: json['batt'],
    );
  }
}
