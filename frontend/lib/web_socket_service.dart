// import 'dart:convert'; // Za JSON dekodiranje
// import 'package:sockjs_client_wrapper/sockjs_client_wrapper.dart';
// import 'dart:async';
// import 'package:flutter/foundation.dart'; // Potrebno za ChangeNotifier
// import 'package:latlong2/latlong.dart'; // Import latlong2

// class WebSocketService extends ChangeNotifier {
//   late SockJSClient _socket; // Korišćenje SockJSClient umesto SockJS
//   final StreamController<Map<String, dynamic>> _locationController =
//       StreamController<Map<String, dynamic>>.broadcast();

//   // Stream za pristup podacima lokacije
//   Stream<Map<String, dynamic>> get locationStream => _locationController.stream;

//   LatLng _currentLocation = LatLng(0.0, 0.0); // Početna lokacija
//   LatLng get currentLocation => _currentLocation;

//   // Funkcija za povezivanje sa serverom
//   void connect() {
//     _socket = SockJSClient(Uri.parse('ws://localhost:8000/ws/websocket'));

//     _socket.onOpen.listen((event) {
//       print('Konekcija otvorena');
//       _socket.send('{"command":"/subscribe","destination":"/topic/location"}');
//     });

//     _socket.onMessage.listen((event) {
//       print('Received from server: ${event.data}');
//       try {
//         // Ako server šalje JSON, dekodiramo ga u Map<String, dynamic>
//         Map<String, dynamic> locationData = jsonDecode(event.data);
//         _currentLocation = LatLng(
//           locationData['latitude'],
//           locationData['longitude'],
//         );
//         notifyListeners(); // Ažuriramo stanje i obaveštavamo sve slušaoce
//       } catch (e) {
//         print('Error decoding JSON: $e');
//       }
//     });

//     _socket.onClose.listen((event) {
//       print('Connection closed');
//     });
//   }

//   // Funkcija za gašenje servisa
//   void dispose() {
//     _socket.close();
//     _locationController.close();
//     super.dispose(); // Pozivamo super.dispose() kada nasleđujemo ChangeNotifier
//   }
// }
