import 'package:frontend/model/Device.model.dart';

class Group {
  String? id;
  String name;
  Map<String, Device>? devices; // Ključ je ID uređaja

  Group({required this.name});

  factory Group.fromJson(Map<String, dynamic> json) {
    return Group(name: json['name']);
  }
}
