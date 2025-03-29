import 'dart:io';
import 'dart:convert';

import 'package:frontend/global.dart';

class StompSocket {
  static var _nextId = 0;

  final String destination;
  final String hostname;
  final Function(Map<String, dynamic>) callback;

  late WebSocket _socket;
  StompSocket(this.destination, this.callback, {this.hostname = webSocketUrl});

  Future<void> connect() async {
    _socket = await WebSocket.connect(hostname);
    _socket.listen(
      _updateReceived,
      onError: (error) {
        print("WebSocket error: $error");
      },
      onDone: () {
        print("WebSocket connection closed");
      },
    );

    _socket.add(_buildConnectString());
    _socket.add(_buildSubscribeString());
  }

  void disconnect() {
    _socket.add(_buildDisconnectString());
    _socket.listen(
      (data) {
        final lines = data.toString().split('\n');
        if (lines.isNotEmpty && lines[0] == 'RECEIPT') {
          _socket.close();
        }
      },
      onError: (error) {
        print("Error during disconnection: $error");
      },
      onDone: () {
        print("Disconnected from WebSocket");
      },
    );
  }

  void _updateReceived(dynamic data) {
    final lines = data.toString().split('\n');
    if (lines.isNotEmpty) {
      final command = lines[0];

      if (command == "RECEIPT") {
        _socket.close();
      } else if (command == "CONNECTED") {
        print('Connected successfully to $destination @ $hostname');
      } else if (command == "MESSAGE") {
        final indexOfBody = lines.indexWhere((line) => line.isEmpty) + 1;
        if (indexOfBody < lines.length) {
          final bodyLine = lines[indexOfBody];
          try {
            Map<String, dynamic> json = jsonDecode(bodyLine);
            callback(json);
          } catch (e) {
            print("Error parsing message body: $e");
          }
        }
      }
    }
  }

  void reconnect() async {
    print("Attempting to reconnect...");
    await Future.delayed(Duration(seconds: 5)); // Reconnect after 5 seconds
    await connect();
  }

  String _buildConnectString() {
    return 'CONNECT\naccept-version:1.2\nhost:$hostname\n\n\x00';
  }

  String _buildSubscribeString() {
    // we increase the nextId so that other listener don't choose the same for a subscription
    // since this could lead to strange behavior
    _nextId++;
    var id = _nextId;
    return 'SUBSCRIBE\nid:$id\ndestination:$destination\nack:client\n\n\x00';
  }

  String _buildDisconnectString() {
    return 'DISCONNECT\nreceipt:77\n\n\x00';
  }
}
