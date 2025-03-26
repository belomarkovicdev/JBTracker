import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:go_router/go_router.dart';
import '../providers/AuthProvider.dart';

class LoginScreen extends ConsumerWidget {
  final TextEditingController usernameController = TextEditingController();
  final TextEditingController passwordController = TextEditingController();

  @override
  Widget build(BuildContext context, WidgetRef ref) {
    return Scaffold(
      appBar: AppBar(title: Text("Login")),
      body: Padding(
        padding: EdgeInsets.all(16),
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            TextField(
              controller: usernameController,
              decoration: InputDecoration(labelText: 'Username'),
            ),
            TextField(
              controller: passwordController,
              decoration: InputDecoration(labelText: 'Password'),
              obscureText: true,
            ),
            SizedBox(height: 16),
            ElevatedButton(
              onPressed: () {
                final username = usernameController.text;
                final groupId =
                    "67e47ee4a8b11f2acb8486b6"; // Dobijeno iz backend odgovora
                final token =
                    "eyJhbGciOiJIUzM4NCJ9.eyJyb2xlcyI6IlJPTEVfVVNFUiIsInVzZXJJZCI6IjY3ZTFjMjNkMTI3MmYzN2IxNzg4YzA3YiIsInVzZXJuYW1lIjoiam92YW5iZTEiLCJzdWIiOiJqb3ZhbmJlMSIsImlhdCI6MTc0MzAyODY0NCwiZXhwIjoxNzQzMDMwNDQ0fQ.eElL_hOnyWoJ0BH7DFYdLwN1zHYuXkR2gK_zWexjPHguTiG5ctJAObJ4hn3Raiku";
                ref.read(authProvider.notifier).login(username, groupId, token);
                context.go('/home');
              },
              child: Text("Login"),
            ),
          ],
        ),
      ),
    );
  }
}
