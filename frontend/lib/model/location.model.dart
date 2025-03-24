import 'package:frontend/model/Coordinates.model.dart';

class Location {
  Coordinates coordinates;
  String timestamp;
  double accuracy;
  String battery;

  Location({
    required this.coordinates,
    required this.timestamp,
    required this.accuracy,
    required this.battery,
  });

  factory Location.fromJson(Map<String, dynamic> json) {
    return Location(
      coordinates: Coordinates.fromJson(json['coordinates']),
      timestamp: json['timestamp'].toString(),
      accuracy: json['accuracy'],
      battery: json['battery'],
    );
  }
  static List<Location> fromJsonList(List<dynamic> jsonList) {
    return jsonList.map((json) => Location.fromJson(json)).toList();
  }
}
