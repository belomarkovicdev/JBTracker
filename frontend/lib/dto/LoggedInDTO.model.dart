import 'package:frontend/model/User.model.dart';

class LoggedInDTO {
  String token;
  User user;

  LoggedInDTO({required this.token, required this.user});

  factory LoggedInDTO.fromJson(Map<String, dynamic> json) {
    return LoggedInDTO(token: json['token'], user: User.fromJson(json['user']));
  }

  @override
  String toString() {
    return 'LoggedInDTO{token:$token, user: $user}';
  }
}
