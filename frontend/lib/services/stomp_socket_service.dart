import 'dart:convert';
import 'dart:io';
import 'package:flutter/foundation.dart';
import 'package:web_socket_channel/io.dart';
import 'package:web_socket_channel/web_socket_channel.dart';
import 'package:web_socket_channel/html.dart';

class StompSocketService {
  static var _nextId = 0;

  final String destination;
  final Function(Map<String, dynamic>) onMessage;

  late WebSocketChannel _channel;

  StompSocketService({required this.destination, required this.onMessage});

  Future<void> connect() async {
    final url = await _buildUrl();
    print('[STOMP] Connecting to $url'); // LOG: connection start

    if (kIsWeb) {
      _channel = HtmlWebSocketChannel.connect(url);
    } else {
      _channel = IOWebSocketChannel.connect(url);
    }

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

  Future<String> _buildUrl() async {
    if (kIsWeb) {
      return 'ws://localhost:8000/ws';
    } else {
      if (Platform.isAndroid) {
        if (await _isEmulator()) {
          return 'ws://10.0.2.2:8000/ws';
        } else {
          final localIp = await _getLocalIp();
          return 'ws://$localIp:8000/ws';
        }
      } else {
        final localIp = await _getLocalIp();
        return 'ws://$localIp:8000/ws';
      }
    }
  }

  Future<String> _getLocalIp() async {
    for (var interface in await NetworkInterface.list()) {
      for (var addr in interface.addresses) {
        if (addr.type == InternetAddressType.IPv4 && !addr.isLoopback) {
          return addr.address;
        }
      }
    }
    throw Exception('Cannot find local IP address');
  }

  Future<bool> _isEmulator() async {
    try {
      final result = await InternetAddress.lookup('10.0.2.2');
      return result.isNotEmpty;
    } catch (_) {}
    return false;
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
