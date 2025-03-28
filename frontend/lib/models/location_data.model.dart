class LocationData {
  final double lat;
  final double lon;
  final String timestamp;

  LocationData({required this.lat, required this.lon, required this.timestamp});

  factory LocationData.fromJson(Map<String, dynamic> json) {
    return LocationData(
      lat: (json['coordinates']['x'] as num).toDouble(),
      lon: (json['coordinates']['y'] as num).toDouble(),
      timestamp: json['timestamp'] as String,
    );
  }
  @override
  String toString() {
    // TODO: implement toString
    return "X:$lat,Y:$lon";
  }
}
