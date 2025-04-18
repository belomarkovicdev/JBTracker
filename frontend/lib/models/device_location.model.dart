class DeviceLocation {
  final String id;
  final double latitude;
  final double longitude;
  final double accuracy;
  final String battery;

  DeviceLocation({
    required this.id,
    required this.latitude,
    required this.longitude,
    required this.accuracy,
    required this.battery,
  });

  factory DeviceLocation.fromJson(Map<String, dynamic> json) {
    return DeviceLocation(
      id: json['id'] as String,
      latitude: json['latitude'] as double,
      longitude: json['longitude'] as double,
      accuracy: json['accuracy'] as double,
      battery: json['battery'] as String,
    );
  }
}
