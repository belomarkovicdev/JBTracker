import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:frontend/models/device_location.model.dart';
import 'package:frontend/services/web_socket_service.dart';

final mapDataProvider =
    StateNotifierProvider<MapDataNotifier, Map<String, List<DeviceLocation>>>((
      ref,
    ) {
      return MapDataNotifier(ref);
    });

class MapDataNotifier extends StateNotifier<Map<String, List<DeviceLocation>>> {
  final Ref ref;
  MapDataNotifier(this.ref) : super({});

  void updateMap(Map<String, List<DeviceLocation>> newData) {
    state = newData;
  }

  void subscribeToWebSocket() {
    final stompSocket = StompSocket('/topic/location', (data) {
      final Map<String, List<DeviceLocation>> updatedData = {};

      // Prolazimo kroz podatke i ažuriramo mapu
      data.forEach((deviceId, locations) {
        updatedData[deviceId] = List<DeviceLocation>.from(
          locations.map((loc) => DeviceLocation.fromJson(loc)),
        );
      });

      updateMap(updatedData);
    });

    stompSocket.connect();
  }
}
