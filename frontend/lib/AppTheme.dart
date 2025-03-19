import 'package:flutter/material.dart';

class AppTheme {
  // This is where you define your app's theme
  static ThemeData get theme {
    return ThemeData(
      // App-wide settings
      primarySwatch: Colors.green,
      // ElevatedButton customization
      elevatedButtonTheme: ElevatedButtonThemeData(
        style: ElevatedButton.styleFrom(
          shape: RoundedRectangleBorder(
            borderRadius: BorderRadius.zero, // Rectangular buttons
          ),
        ),
      ),
      // Additional theme properties like TextTheme, AppBar, etc. can go here
      appBarTheme: AppBarTheme(
        backgroundColor: Colors.green,
        titleTextStyle: TextStyle(color: Colors.black),
      ),
    );
  }
}
