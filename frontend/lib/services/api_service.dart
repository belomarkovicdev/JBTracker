import 'dart:convert';
import 'package:frontend/global.dart';
import 'package:http/http.dart' as http;

Future<String?> login(String username, String password) async {
  print(username);
  print(password);
  final url = Uri.parse(loginUrl);

  final response = await http.post(
    url,
    body: json.encode({'username': username, 'password': password}),
    headers: {'Content-Type': 'application/json'},
  );

  if (response.statusCode == 200) {
    final data = json.decode(response.body);
    return data['token']; // Assuming the response contains the token in 'token' field
  } else {
    return null;
  }
}
