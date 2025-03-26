class Location {
  final double lat;
  final double lon;
  final String timestamp;

  Location({required this.lat, required this.lon, required this.timestamp});

  factory Location.fromJson(Map<String, dynamic> json) {
    return Location(
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
