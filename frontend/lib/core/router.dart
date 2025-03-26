import 'package:frontend/screen/HomeScreen.dart';
import 'package:frontend/screen/LoginScreen.dart';
import 'package:frontend/screen/MapScreen.dart';
import 'package:go_router/go_router.dart';

final GoRouter router = GoRouter(
  initialLocation: '/login',
  routes: [
    GoRoute(path: '/login', builder: (context, state) => LoginScreen()),
    GoRoute(path: '/home', builder: (context, state) => HomeScreen()),
    GoRoute(
      path: '/map/:groupId',
      builder: (context, state) {
        final groupId = state.pathParameters['groupId']!;
        return MapScreen(groupId: groupId);
      },
    ),
  ],
);
