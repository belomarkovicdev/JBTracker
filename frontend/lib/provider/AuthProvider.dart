import 'package:flutter/material.dart';
import 'package:frontend/model/User.model.dart';
import 'package:frontend/service/LoginService.dart';
import 'package:frontend/service/UserService.dart';
import 'package:shared_preferences/shared_preferences.dart';

class AuthProvider with ChangeNotifier {
  bool _isLoggedIn = false;
  bool _isStatusChecked =
      false; // Ova varijabla prati da li je status već proveren
  User? _loggedInUser;
  bool get isLoggedIn => _isLoggedIn;
  User? get loggedInUser => _loggedInUser;
  // Check if the user is logged in when the app starts
  Future<bool> checkLoginStatus() async {
    Userservice userService = Userservice();
    if (_isStatusChecked) {
      return _isLoggedIn; // Ako je status već proveravan, odmah vrati vrednost
    }
    final SharedPreferences prefs = await SharedPreferences.getInstance();
    String? token = prefs.getString('auth_token');
    _isLoggedIn = token != null; // Ako token nije null, korisnik je logovan
    var userId = LoginService().getUserIdFromToken(token!);
    _loggedInUser = await userService.getUser(userId);
    _isStatusChecked = true; // Oznaka da je status logovanja proveren
    notifyListeners(); // Obavesti UI da se stanje promenilo

    return _isLoggedIn; // Vraćamo status logovanja
  }

  set loggedInUser(User? user) {
    _loggedInUser = user;
    notifyListeners(); // Obaveštava sve zainteresovane widgete da je korisnik promenjen
  }

  // Logovanje
  Future<void> login(String token, User loggedInUser) async {
    final SharedPreferences prefs = await SharedPreferences.getInstance();
    await prefs.setString('auth_token', token);
    _isLoggedIn = true;
    _loggedInUser = loggedInUser;
    _isStatusChecked = true; // Označi da je logovanje obavljeno
    notifyListeners();
  }

  // Odjava
  Future<void> logout() async {
    final SharedPreferences prefs = await SharedPreferences.getInstance();
    await prefs.remove('auth_token');
    _isLoggedIn = false;
    _loggedInUser = null;
    _isStatusChecked = true; // Označi da je odjava obavljena
    notifyListeners();
  }
}
