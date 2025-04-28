class DeviceLocation {
  final String id;
  final double latitude;
  final double longitude;
  final double accuracy;
  final int timestamp;
  final String battery;

  DeviceLocation({
    required this.id,
    required this.latitude,
    required this.longitude,
    required this.accuracy,
    required this.timestamp,
    required this.battery,
  });

  factory DeviceLocation.fromJson(Map<String, dynamic> json) {
    return DeviceLocation(
      id: json['id'] as String,
      latitude: json['lat'] as double,
      longitude: json['lon'] as double,
      accuracy: json['accuracy'] as double,
      timestamp: json['timestamp'] as int,
      battery: json['speed'] as String,
    );
  }
}
