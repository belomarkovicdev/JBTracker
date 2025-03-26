import 'dart:convert';

import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:frontend/global.dart';
import 'package:frontend/model/Group.model.dart';
import 'package:frontend/providers/AuthProvider.dart';
import 'package:http/http.dart' as http;

final mapDataProvider = FutureProvider.family<Group, String>((
  ref,
  groupId,
) async {
  final user = ref.read(authProvider);
  if (user == null) throw Exception("Unauthorized: No user found");
  print(user);

  final response = await http.get(
    Uri.parse('http://$serverUrl/api/group/$groupId/overview'),
    headers: {
      'Authorization': 'Bearer ${user.token}',
      'Content-Type': 'application/json',
    },
  );

  if (response.statusCode == 200) {
    final data = json.decode(response.body);

    if (data['locations'] == null) {
      print("Warning: `locations` is null, setting empty list.");
      data['locations'] = [];
    }

    return Group.fromJson(data);
  } else {
    throw Exception('Failed to load data: ${response.statusCode}');
  }
});
