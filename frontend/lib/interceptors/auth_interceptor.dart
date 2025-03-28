import 'dart:async';

import 'package:frontend/providers/auth_provider.dart';
import 'package:http_interceptor/http_interceptor.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';

class AuthInterceptor implements InterceptorContract {
  final Ref ref; // Ako želiš da pristupiš providerima

  AuthInterceptor(this.ref);

  @override
  FutureOr<BaseRequest> interceptRequest({required BaseRequest request}) async {
    // Ovdje možeš dodati logiku za izmene zahteva
    final token = ref.read(authProvider); // Pristup tokenu iz providera

    if (token != null) {
      // Dodaj Authorization header ako postoji token
      request.headers['Authorization'] = token;
    }

    return request; // Vraća presretnuti zahtev
  }

  @override
  FutureOr<BaseResponse> interceptResponse({
    required BaseResponse response,
  }) async {
    // Ovdje možeš dodati logiku za obradu odgovora
    if (response.statusCode == 401) {
      // Na primer, ako dođe do 401 Unauthorized, možeš obraditi grešku
      print("Unauthorized request");
    }

    return response; // Vraća presretnuti odgovor
  }

  @override
  FutureOr<bool> shouldInterceptRequest() async {
    return true; // Uvek presrećemo zahtev
  }

  @override
  FutureOr<bool> shouldInterceptResponse() async {
    return true; // Uvek presrećemo odgovor
  }
}
