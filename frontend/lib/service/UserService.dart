import 'package:flutter/material.dart';
import 'package:frontend/global.dart';
import 'package:frontend/model/User.model.dart';
import 'package:frontend/provider/AuthProvider.dart';
import 'package:provider/provider.dart';
import 'package:shared_preferences/shared_preferences.dart';
import 'dart:convert';
import 'package:http/http.dart' as http;

class Userservice {
  Future<String?> getToken() async {
    final prefs = await SharedPreferences.getInstance();
    // Preuzimanje tokena iz SharedPreferences
    return prefs.getString('auth_token');
  }

  Future<User?> getLoggedInUser(BuildContext context) async {
    // Provodi asinhrono čekanje na podatke o korisniku iz AuthProvider
    final authProvider = Provider.of<AuthProvider>(context, listen: false);

    // // Ako podaci nisu već učitani, čekaj dok ne budu
    // await authProvider
    //     .loadUser(); // Pretpostavljamo da loadUser() učitava korisnika asinhrono
    return authProvider.loggedInUser; // Vraća korisnika
  }

  // Funkcija za preuzimanje korisnika sa servera
  Future<User?> getUser(String userId) async {
    try {
      final response = await http.get(
        Uri.parse('$serverUrl/api/users/$userId'),
      );

      // Ako statusni kod nije 200, baca grešku
      if (response.statusCode == 200) {
        // Ako je odgovor uspešan, parsiraj JSON i vrati User objekat
        final data = json.decode(response.body);
        return User.fromJson(
          data,
        ); // Pretpostavljamo da imaš User.fromJson metodu za dekodiranje
      } else {
        // Ako server vrati grešku, baci odgovarajući izuzetak
        throw Exception(
          'Greška pri preuzimanju korisnika: ${response.statusCode}',
        );
      }
    } catch (error) {
      // Ako dođe do greške u komunikaciji, baci izuzetak
      throw Exception('Greška pri povezivanju sa serverom: $error');
    }
  }
}
