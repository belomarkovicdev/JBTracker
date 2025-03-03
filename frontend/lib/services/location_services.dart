import 'dart:convert';
import 'package:http/http.dart' as http;

class LocationService {
  final String apiUrl = "http://localhost:8000/api/location"; // Backend API URL

  Future<Map<String, dynamic>> fetchLocation() async {
    try {
      final response = await http.get(Uri.parse(apiUrl));

      if (response.statusCode == 200) {
        return json.decode(
          response.body,
        ); // Podaci o lokaciji (npr. latitude, longitude)
      } else {
        throw Exception("Failed to load location");
      }
    } catch (e) {
      throw Exception("Failed to load location: $e");
    }
  }
}
