class Device {
  String id;
  String deviceId;
  String name;

  Device({required this.id, required this.deviceId, required this.name});

  factory Device.fromJson(Map<String, dynamic> json) {
    return Device(
      id: json['id'],
      deviceId: json['deviceId'],
      name: json['name'],
    );
  }
  static List<Device> fromJsonListToList(List<dynamic> jsonList) {
    return jsonList.map((json) => Device.fromJson(json)).toList();
  }

  static Map<String, Device> fromJsonListToMap(Map<String, dynamic> json) {
    Map<String, Device> devices = {};

    json.forEach((key, value) {
      devices[key] = Device.fromJson(value);
    });

    return devices;
  }
}
