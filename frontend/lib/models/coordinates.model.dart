class Coordinates {
  final double x;
  final double y;

  Coordinates({required this.x, required this.y});

  factory Coordinates.fromJson(Map<String, dynamic> json) {
    return Coordinates(x: json['x'], y: json['y']);
  }
}
