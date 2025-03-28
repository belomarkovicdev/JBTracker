import 'dart:convert';

import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:frontend/global.dart';
import 'package:frontend/interceptors/auth_interceptor.dart';
import 'package:frontend/models/group.model.dart';
import 'package:http/http.dart' as http;
import 'package:http_interceptor/http_interceptor.dart';

final httpClientProvider = Provider<http.Client>((ref) {
  return InterceptedClient.build(interceptors: [AuthInterceptor(ref)]);
});

final mapDataProvider = FutureProvider<Group>((ref) async {
  final client = ref.read(httpClientProvider);

  final response = await client.get(
    Uri.parse(getMapUrl),
    headers: {'Content-Type': 'application/json'},
  );

  if (response.statusCode == 200) {
    final data = json.decode(response.body);
    return Group.fromJson(data);
  } else {
    throw Exception('Failed to load data: ${response.statusCode}');
  }
});
