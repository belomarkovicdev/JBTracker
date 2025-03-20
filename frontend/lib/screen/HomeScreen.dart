import 'package:flutter/material.dart';
import 'package:frontend/provider/AuthProvider.dart';
import 'package:frontend/screen/LoginScreen.dart';
import 'package:provider/provider.dart';

class HomeScreen extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: Text('Početna Stranica')),
      body: Center(
        child: Column(
          children: [
            ElevatedButton(
              onPressed: () {
                Navigator.pushNamed(context, '/map');
              },
              child: Text('Map'),
            ),
            ElevatedButton(
              onPressed: () {
                // Navigacija prema ruti '/second'
                Navigator.pushNamed(context, '/profile');
              },
              child: Text('Profil'),
            ),
            ElevatedButton(
              onPressed: () async {
                await Provider.of<AuthProvider>(
                  context,
                  listen: false,
                ).logout();
                Navigator.pushReplacement(
                  context,
                  MaterialPageRoute(builder: (_) => LoginScreen()),
                );
              },
              child: Text('Izloguj se'),
            ),
          ],
        ),
      ),
    );
  }
}
