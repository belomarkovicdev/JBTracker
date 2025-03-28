// main.dart
import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:frontend/pages/home_page.dart';
import 'package:frontend/pages/login_page.dart';
import 'package:frontend/pages/map_page.dart';
import 'package:go_router/go_router.dart';

void main() {
  runApp(ProviderScope(child: MyApp()));
}

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    final goRouter = GoRouter(
      initialLocation: '/login',
      routes: [
        GoRoute(path: '/login', builder: (context, state) => LoginPage()),
        GoRoute(path: '/home', builder: (context, state) => HomePage()),
        GoRoute(path: '/map', builder: (context, state) => MapPage()),
      ],
    );

    return MaterialApp.router(
      title: 'Flutter Login Example',
      routerConfig: goRouter,
      theme: ThemeData(primarySwatch: Colors.blue),
    );
  }
}
