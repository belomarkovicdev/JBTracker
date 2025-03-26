import 'package:frontend/model/Location.model.dart';

class Group {
  final String name;
  final List<Location> locations;

  Group({required this.name, required this.locations});

  factory Group.fromJson(Map<String, dynamic> json) {
    List<Location> allLocations = [];

    // Prolazimo kroz sve sesije i skupljamo lokacije
    if (json['deviceTrackingSessions'] != null) {
      for (var session in json['deviceTrackingSessions']) {
        allLocations.addAll(
          (session['locations'] as List<dynamic>?)
                  ?.map((loc) => Location.fromJson(loc))
                  .toList() ??
              [],
        );
      }
    }

    return Group(name: json['name'] as String, locations: allLocations);
  }

  @override
  String toString() {
    // TODO: implement toString
    return "group:$name, locations:$locations";
  }
}
