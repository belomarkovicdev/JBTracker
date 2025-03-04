// import 'dart:async';

// import 'package:flutter/material.dart';
// import 'package:flutter_map/flutter_map.dart';
// import 'package:frontend/web_socket_service.dart';
// import 'package:latlong2/latlong.dart';
// import 'package:provider/provider.dart';

// class LocationMapScreen extends StatefulWidget {
//   @override
//   _LocationMapScreenState createState() => _LocationMapScreenState();
// }

// class _LocationMapScreenState extends State<LocationMapScreen> {
//   late WebSocketService _webSocketService;
//   late StreamSubscription _locationSubscription;
//   LatLng _currentLocation = LatLng(0.0, 0.0); // Početna lokacija

//   @override
//   void initState() {
//     super.initState();
//     _webSocketService = Provider.of<WebSocketService>(context, listen: false);

//     // Pretplaćivanje na stream podataka lokacije
//     _locationSubscription = _webSocketService.locationStream.listen((
//       locationData,
//     ) {
//       setState(() {
//         _currentLocation = LatLng(
//           locationData['latitude'],
//           locationData['longitude'],
//         );
//       });
//     });

//     // Povezivanje sa SockJS serverom
//     _webSocketService.connect();
//   }

//   @override
//   void dispose() {
//     _locationSubscription.cancel();
//     super.dispose();
//   }

//   @override
//   Widget build(BuildContext context) {
//     return Scaffold(
//       appBar: AppBar(title: Text("Location Map")),
//       body: FlutterMap(
//         options: MapOptions(initialCenter: _currentLocation, initialZoom: 13.0),
//         children: [
//           TileLayer(
//             urlTemplate: "https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png",
//             subdomains: ['a', 'b', 'c'],
//           ),
//           MarkerLayer(
//             markers: [
//               Marker(
//                 width: 80.0,
//                 height: 80.0,
//                 point: _currentLocation,
//                 child: Icon(Icons.location_on, size: 40.0, color: Colors.red),
//               ),
//             ],
//           ),
//         ],
//       ),
//     );
//   }
// }
