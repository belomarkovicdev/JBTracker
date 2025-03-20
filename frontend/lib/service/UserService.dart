import 'package:shared_preferences/shared_preferences.dart';

class Userservice {
  Future<String?> getToken() async {
    final prefs = await SharedPreferences.getInstance();
    // Preuzimanje tokena iz SharedPreferences
    return prefs.getString('auth_token');
  }
}
