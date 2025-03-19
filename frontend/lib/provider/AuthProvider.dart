import 'package:flutter/material.dart';
import 'package:shared_preferences/shared_preferences.dart';

class AuthProvider with ChangeNotifier {
  bool _isLoggedIn = false;
  bool _isStatusChecked =
      false; // Ova varijabla prati da li je status već proveren

  bool get isLoggedIn => _isLoggedIn;

  // Check if the user is logged in when the app starts
  Future<bool> checkLoginStatus() async {
    if (_isStatusChecked) {
      return _isLoggedIn; // Ako je status već proveravan, odmah vrati vrednost
    }
    final SharedPreferences prefs = await SharedPreferences.getInstance();
    String? token = prefs.getString('auth_token');
    _isLoggedIn = token != null; // Ako token nije null, korisnik je logovan
    _isStatusChecked = true; // Oznaka da je status logovanja proveren
    notifyListeners(); // Obavesti UI da se stanje promenilo

    return _isLoggedIn; // Vraćamo status logovanja
  }

  // Logovanje
  Future<void> login(String token) async {
    final SharedPreferences prefs = await SharedPreferences.getInstance();
    await prefs.setString('auth_token', token);
    _isLoggedIn = true;
    _isStatusChecked = true; // Označi da je logovanje obavljeno
    notifyListeners();
  }

  // Odjava
  Future<void> logout() async {
    final SharedPreferences prefs = await SharedPreferences.getInstance();
    await prefs.remove('auth_token');
    _isLoggedIn = false;
    _isStatusChecked = true; // Označi da je odjava obavljena
    notifyListeners();
  }
}
