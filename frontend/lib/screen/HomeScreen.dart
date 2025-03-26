import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:go_router/go_router.dart';
import '../providers/AuthProvider.dart';

class HomeScreen extends ConsumerWidget {
  @override
  Widget build(BuildContext context, WidgetRef ref) {
    return Scaffold(
      appBar: AppBar(title: Text("Home")),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            ElevatedButton(
              onPressed: () {
                final groupId =
                    "67e47ee4a8b11f2acb8486b6"; // Ovde stavi stvarni ID iz autentifikacije
                context.go('/map/$groupId');
              },
              child: Text("Go to Map"),
            ),
            SizedBox(height: 16),
            ElevatedButton(
              onPressed: () {
                ref.read(authProvider.notifier).logout();
                context.go('/login');
              },
              child: Text("Logout"),
            ),
          ],
        ),
      ),
    );
  }
}
