class Location {
  final double x;
  final double y;

  Location({required this.x, required this.y});

  factory Location.fromJson(Map<String, dynamic> json) {
    return Location(x: json['x'], y: json['y']);
  }
}
