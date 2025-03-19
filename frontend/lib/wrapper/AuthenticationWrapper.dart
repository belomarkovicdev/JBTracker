import 'package:flutter/material.dart';
import 'package:frontend/provider/AuthProvider.dart';
import 'package:frontend/screen/HomeScreen.dart';
import 'package:frontend/screen/LoginScreen.dart';
import 'package:provider/provider.dart';

class AuthenticationWrapper extends StatelessWidget {
  const AuthenticationWrapper({super.key});
  @override
  Widget build(BuildContext context) {
    final authProvider = Provider.of<AuthProvider>(context);

    return FutureBuilder<bool>(
      future:
          authProvider
              .checkLoginStatus(), // Pozivamo asinhroni poziv da proverimo status logovanja
      builder: (context, snapshot) {
        if (snapshot.connectionState == ConnectionState.waiting) {
          // Ako se čekaju podaci, prikaži loading indikator
          return Center(child: CircularProgressIndicator());
        }

        if (snapshot.hasError) {
          // Ako dođe do greške, prikaži poruku o grešci
          return Center(child: Text('Došlo je do greške.'));
        }

        // Kada je status proveren, prikaži odgovarajući ekran
        return authProvider.isLoggedIn ? HomeScreen() : LoginScreen();
      },
    );
  }
}
