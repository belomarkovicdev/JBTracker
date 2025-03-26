import 'package:frontend/model/Location.model.dart';

class DeviceTrackingSession {
  final String deviceId;
  final List<Location> locations;

  DeviceTrackingSession({required this.deviceId, required this.locations});

  factory DeviceTrackingSession.fromJson(Map<String, dynamic> json) {
    return DeviceTrackingSession(
      deviceId: json['deviceId'] as String,
      locations:
          (json['locations'] as List<dynamic>?)
              ?.map((loc) => Location.fromJson(loc))
              .toList() ??
          [],
    );
  }
}
