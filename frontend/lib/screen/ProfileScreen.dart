import 'package:flutter/material.dart';
import 'package:frontend/service/LoginService.dart';
import 'package:shared_preferences/shared_preferences.dart';

import 'UpdateProfileScreen.dart';

class ProfileScreen extends StatefulWidget {
  const ProfileScreen({super.key});

  @override
  _ProfileScreenState createState() => _ProfileScreenState();
}

class _ProfileScreenState extends State<ProfileScreen> {
  String username = "";
  String email = "";
  String roles = "";

  @override
  void initState() {
    super.initState();
    _loadUserData();
  }

  Future<void> _loadUserData() async {
    final prefs = await SharedPreferences.getInstance();
    setState(() {
      var token = prefs.getString('auth_token'); // Set the token after loading
      Map<String, dynamic> claims = LoginService().getClaimsFromToken(token!);
      username = claims["username"];
      email = claims["email"];
    });
  }

  void updateProfile(String username, String email) {
    setState(() {
      this.username = username;
      this.email = email;
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: Text('Profil')),
      body: Padding(
        padding: const EdgeInsets.all(16.0),
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [
            Text("Korisnicko ime: $username", style: TextStyle(fontSize: 18)),
            Text("Email: $email", style: TextStyle(fontSize: 18)),
            SizedBox(height: 20),
            ElevatedButton(
              onPressed: () async {
                final result = await Navigator.push(
                  context,
                  MaterialPageRoute(
                    builder:
                        (context) => UpdateProfileScreen(
                          username: username,
                          email: email,
                        ),
                  ),
                );

                if (result != null) {
                  updateProfile(result['name'], result['email']);
                }
              },
              child: Text("Izmeni podatke"),
            ),
          ],
        ),
      ),
    );
  }
}
