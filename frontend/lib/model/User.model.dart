import 'package:frontend/model/Device.model.dart';
import 'package:frontend/model/Group.model.dart';

class User {
  String id;
  String username;
  String email;
  List<Device> devices;
  Group group;

  User({
    required this.id,
    required this.username,
    required this.email,
    required this.devices,
    required this.group,
  });

  factory User.fromJson(Map<String, dynamic> json) {
    return User(
      id: json['id'],
      username: json['username'],
      email: json['email'],
      devices: Device.fromJsonListToList(json['devices']),
      group: Group.fromJson(json['group']),
    );
  }
  Map<String, dynamic> toJson() {
    return {'id': id, 'username': username, 'email': email};
  }

  @override
  String toString() {
    return 'id:$id, username:$username, email:$email, devices:$devices, group:$group';
  }
}
