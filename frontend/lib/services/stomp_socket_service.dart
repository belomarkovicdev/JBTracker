import 'dart:convert';
import 'package:frontend/global.dart';

import '../services/socket/web_socket_stub.dart'
    if (dart.library.html) '../services/socket/web_socket_html.dart'
    if (dart.library.io) '../services/socket/web_socket_io.dart';
import 'package:web_socket_channel/web_socket_channel.dart';

class StompSocketService {
  static var _nextId = 0;

  final String destination;
  final Function(Map<String, dynamic>) onMessage;

  late WebSocketChannel _channel;

  StompSocketService({required this.destination, required this.onMessage});

  Future<void> connect() async {
    final url = webSocketUrl;
    print('[STOMP] Connecting to $url'); // LOG: connection start
    _channel = createWebSocketChannel(url);

    _channel.stream.listen(
      _handleMessage,
      onError: (error) => print('[STOMP] Error: $error'),
      onDone: () => print('[STOMP] Connection closed'),
    );

    _send(_buildConnectFrame());
    _send(_buildSubscribeFrame());
  }

  void disconnect() {
    _send(_buildDisconnectFrame());
    _channel.sink.close();
  }

  void _handleMessage(dynamic data) {
    print('[STOMP] RECEIVED: $data'); // LOG: received message

    final lines = data.toString().split('\n');
    if (lines.isEmpty) return;

    final command = lines[0];
    if (command == 'CONNECTED') {
      print('[STOMP] Connected.');
    } else if (command == 'MESSAGE') {
      final indexOfBody = lines.indexWhere((line) => line.isEmpty) + 1;

      if (indexOfBody < lines.length) {
        var bodyLine = lines[indexOfBody];
        if (bodyLine.endsWith('\x00')) {
          bodyLine = bodyLine.substring(0, bodyLine.length - 1);
        }

        try {
          Map<String, dynamic> json = jsonDecode(bodyLine);
          onMessage(json);
        } catch (e) {
          print('[STOMP] JSON parse error: $e\nBODY: $bodyLine');
        }
      }
    } else if (command == 'RECEIPT') {
      print('[STOMP] Receipt received.');
    } else {
      print('[STOMP] Unhandled frame: $command');
    }
  }

  void _send(String frame) {
    print('[STOMP] SEND: $frame'); // LOG: sent message
    _channel.sink.add(frame);
  }

  String _buildConnectFrame() {
    return 'CONNECT\naccept-version:1.2\nhost:localhost\n\n\x00';
  }

  String _buildSubscribeFrame() {
    _nextId++;
    return 'SUBSCRIBE\nid:$_nextId\ndestination:$destination\nack:auto\n\n\x00';
  }

  String _buildDisconnectFrame() {
    return 'DISCONNECT\nreceipt:77\n\n\x00';
  }
}
