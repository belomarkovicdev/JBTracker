// home_page.dart
import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:frontend/providers/auth_provider.dart';
import 'package:go_router/go_router.dart';

class HomePage extends ConsumerWidget {
  const HomePage({super.key});

  @override
  Widget build(BuildContext context, WidgetRef ref) {
    final token = ref.watch(authProvider);

    // If no token, redirect to login page
    if (token == null) {
      // Redirect to login if no token
      Future.microtask(() => context.go('/login'));
    }

    return Scaffold(
      appBar: AppBar(title: Text('Home')),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          crossAxisAlignment: CrossAxisAlignment.center,
          children: [
            // If there's no token, let the user know (this condition should already redirect them to login)
            if (token == null)
              Text('You are not logged in. Redirecting to login page...')
            // Buttons for authenticated users
            else ...[
              ElevatedButton(
                onPressed: () {
                  ref
                      .read(authProvider.notifier)
                      .removeToken(); // Remove the token
                  context.go('/login'); // Navigate to login page
                },
                child: Text('Logout'),
              ),
              SizedBox(height: 20),
              ElevatedButton(
                onPressed: () {
                  context.go('/map'); // Navigate to Map page
                },
                child: Text('Go to Map'),
              ),
            ],
          ],
        ),
      ),
    );
  }
}
