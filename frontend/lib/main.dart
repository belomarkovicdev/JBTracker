import 'package:flutter/material.dart';
import 'package:frontend/location_map_screen.dart';
import 'package:provider/provider.dart';
import 'web_socket_service.dart';

void main() {
  runApp(MyApp());
}

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MultiProvider(
      providers: [ChangeNotifierProvider(create: (_) => WebSocketService())],
      child: MaterialApp(
        title: 'Flutter WebSocket',
        theme: ThemeData(primarySwatch: Colors.blue),
        home: LocationMapScreen(),
      ),
    );
  }
}
