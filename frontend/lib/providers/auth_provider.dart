// auth_provider.dart
import 'package:flutter_riverpod/flutter_riverpod.dart';

final authProvider = StateNotifierProvider<AuthNotifier, String?>((ref) {
  return AuthNotifier();
});

class AuthNotifier extends StateNotifier<String?> {
  AuthNotifier() : super(null);

  // Set token
  void setToken(String token) {
    state = token;
  }

  // Remove token (log out)
  void removeToken() {
    state = null;
  }
}
