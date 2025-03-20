import 'package:flutter/material.dart';
import 'package:frontend/provider/AuthProvider.dart';
import 'package:frontend/screen/LoginScreen.dart';
import 'package:provider/provider.dart';

class HomeScreen extends StatelessWidget {
  final Map<String, String> buttons = {"Mapa": "/map", "Profil": "/profile"};
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: Text('JBPetTracker')),
      body: Center(
        child: Column(
          children: [
            ...buttons.entries.map((e) {
              return SizedBox(
                width: 300,
                child: ElevatedButton(
                  onPressed: () {
                    Navigator.pushNamed(context, e.value);
                  },
                  child: Text(e.key),
                ),
              );
            }),
            SizedBox(
              width: 200,
              child: ElevatedButton(
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
            ),
          ],
        ),
      ),
    );
  }
}
