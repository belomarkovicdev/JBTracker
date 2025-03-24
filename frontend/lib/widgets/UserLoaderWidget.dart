// import 'package:flutter/material.dart';
// import 'package:frontend/provider/AuthProvider.dart';
// import 'package:provider/provider.dart';

// class UserLoaderWidget extends StatelessWidget {
//   @override
//   Widget build(BuildContext context) {
//     return FutureBuilder(
//       future: Provider.of<AuthProvider>(context, listen: false).loggedInUser,
//       builder: (context, snapshot) {
//         if (snapshot.connectionState == ConnectionState.waiting) {
//           return CircularProgressIndicator();
//         }

//         if (snapshot.hasError) {
//           return Text('Greška pri učitavanju korisnika');
//         }

//         final loggedInUser = Provider.of<AuthProvider>(context).loggedInUser;
//         if (loggedInUser == null) {
//           return Text('Korisnik nije prijavljen');
//         }

//         return Text('Dobrodošao, ${loggedInUser.username}');
//       },
//     );
//   }
// }
