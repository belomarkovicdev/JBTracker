import 'dart:convert';

import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:frontend/global.dart';
import 'package:frontend/models/group.model.dart';
import 'package:frontend/providers/auth_provider.dart';
import 'package:http/http.dart' as http;

final mapDataProvider = FutureProvider<Group>((ref) async {
  final token = ref.read(authProvider);
  if (token == null) throw Exception("Unauthorized: No user found");

  final response = await http.get(
    Uri.parse(getMapUrl),
    headers: {
      'Authorization': 'Bearer $token', // Add token back
      'Content-Type': 'application/json',
    },
  );

  if (response.statusCode == 200) {
    final data = json.decode(response.body);
    return Group.fromJson(data);
  } else {
    throw Exception('Failed to load data: ${response.statusCode}');
  }
});
