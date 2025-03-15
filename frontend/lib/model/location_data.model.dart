import 'package:frontend/model/location.model.dart';

class LocationData {
  final String deviceId;
  final Coordinates coordinates;
  final double accuracy;
  final String batt;

  LocationData({
    required this.deviceId,
    required this.coordinates,
    required this.accuracy,
    required this.batt,
  });

  factory LocationData.fromJson(Map<String, dynamic> json) {
    return LocationData(
      deviceId: json['id'].toString(), // Extract deviceId from the JSON
      coordinates: Coordinates.fromJson(json['coordinates']),
      accuracy: json['accuracy'],
      batt: json['batt'],
    );
  }
}
