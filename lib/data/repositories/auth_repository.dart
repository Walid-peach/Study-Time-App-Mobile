import 'package:firebase_auth/firebase_auth.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:riverpod_annotation/riverpod_annotation.dart';

part 'auth_repository.g.dart';

@Riverpod(keepAlive: true)
AuthRepository authRepository(Ref ref) =>
    AuthRepository(FirebaseAuth.instance);

class AuthRepository {
  final FirebaseAuth _auth;
  const AuthRepository(this._auth);

  User? get currentUser => _auth.currentUser;

  Future<User> login(String email, String password) async {
    final result =
        await _auth.signInWithEmailAndPassword(email: email, password: password);
    return result.user!;
  }

  Future<User> register(String email, String password) async {
    final result = await _auth.createUserWithEmailAndPassword(
        email: email, password: password);
    return result.user!;
  }

  Future<void> sendPasswordReset(String email) =>
      _auth.sendPasswordResetEmail(email: email);

  Future<void> signOut() => _auth.signOut();
}
