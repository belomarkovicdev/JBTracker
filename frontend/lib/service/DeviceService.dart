import 'dart:convert';

import 'package:frontend/global.dart';
import 'package:http/http.dart' as http;
import 'package:frontend/model/DeviceLocations.model.dart';

class DeviceService {
  final String apiUrl = "http://$serverUrl";

  Future<List<DeviceLocations>> getDevicesFromGroup(String groupId) async {
    final response = await http.get(
      Uri.parse('$apiUrl/api/redis/group/$groupId/devices'),
    );

    if (response.statusCode == 200) {
      List<DeviceLocations> devices = [];
      Map<String, dynamic> data = jsonDecode(response.body);

      data.forEach((key, value) {
        devices.add(DeviceLocations.fromJson(value));
      });

      return devices;
    } else {
      throw Exception('Failed to load devices');
    }
  }
}
