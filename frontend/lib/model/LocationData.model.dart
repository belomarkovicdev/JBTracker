import 'package:frontend/model/Location.model.dart';

class LocationData {
  final String id;
  final Coordinates coordinates;
  final double accuracy;
  final String batt;
  final String timestamp;

  LocationData({
    required this.id,
    required this.coordinates,
    required this.accuracy,
    required this.batt,
    required this.timestamp,
  });

  factory LocationData.fromJson(Map<String, dynamic> json) {
    return LocationData(
      id: json['id'].toString(), // Extract deviceId from the JSON
      coordinates: Coordinates.fromJson(json['coordinates']),
      timestamp: json['timestamp'].toString(),
      accuracy: json['accuracy'],
      batt: json['battery'],
    );
  }
}
