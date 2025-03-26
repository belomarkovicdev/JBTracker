import 'package:flutter_riverpod/flutter_riverpod.dart';

class User {
  final String username;
  final String groupId;
  final String token;

  User({required this.username, required this.groupId, required this.token});
  @override
  String toString() {
    // TODO: implement toString
    return "username:$username,groupId:$groupId,token:$token";
  }
}

class AuthNotifier extends StateNotifier<User?> {
  AuthNotifier() : super(null);

  void login(String username, String groupId, String token) {
    state = User(username: username, groupId: groupId, token: token);
  }

  void logout() {
    state = null;
  }
}

final authProvider = StateNotifierProvider<AuthNotifier, User?>((ref) {
  return AuthNotifier();
});
