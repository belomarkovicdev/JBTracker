import 'dart:convert';
import 'package:frontend/dto/LoggedInDTO.model.dart';
import 'package:http/http.dart' as http;
import 'package:shared_preferences/shared_preferences.dart';
import 'package:jwt_decoder/jwt_decoder.dart';
import 'package:frontend/global.dart';

class LoginService {
  static const String baseUrl = "http://$serverUrl/api/auth";

  Future<void> storeToken(String token) async {
    final SharedPreferences prefs = await SharedPreferences.getInstance();
    await prefs.setString('auth_token', token);
  }

  // create class for claims
  Map<String, dynamic> getClaimsFromToken(String token) {
    return JwtDecoder.decode(token);
  }

  String getUsernameFromToken(String token) {
    return JwtDecoder.decode(token)["username"];
  }

  String getUserIdFromToken(String token) {
    return JwtDecoder.decode(token)["userId"];
  }

  List<String> getUserRolesFromToken(String token) {
    return JwtDecoder.decode(token)["roles"];
  }

  Future<bool> isLoggedIn() async {
    final SharedPreferences prefs = await SharedPreferences.getInstance();
    String? token = prefs.getString('auth_token');
    return token != null;
  }

  static Future<LoggedInDTO> login(String username, String password) async {
    final url = Uri.parse('$baseUrl/login');

    final body = {'username': username, 'password': password};

    final response = await http.post(
      url,
      headers: {'Content-Type': 'application/json'},
      body: jsonEncode(body),
    );

    if (response.statusCode == 200) {
      return LoggedInDTO.fromJson(jsonDecode(response.body));
    } else {
      throw Exception('Failed to login: ${response.statusCode}');
    }
  }
}
