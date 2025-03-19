import 'package:flutter/material.dart';
import 'package:frontend/AppTheme.dart';
import 'package:frontend/provider/AuthProvider.dart';
import 'package:frontend/wrapper/AuthenticationWrapper.dart';
import 'package:provider/provider.dart';

void main() {
  runApp(MyApp());
}

class MyApp extends StatefulWidget {
  @override
  _MyAppState createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
  @override
  Widget build(BuildContext context) {
    return ChangeNotifierProvider(
      create: (_) => AuthProvider(),
      child: MaterialApp(
        title: "JBPetTracker",
        theme: AppTheme.theme,
        home: AuthenticationWrapper(),
      ),
    );
  }
}
