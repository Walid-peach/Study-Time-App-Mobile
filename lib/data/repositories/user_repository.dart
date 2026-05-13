import 'package:cloud_firestore/cloud_firestore.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:riverpod_annotation/riverpod_annotation.dart';
import '../models/user.dart';
import '../models/user_profile_update.dart';

part 'user_repository.g.dart';

@Riverpod(keepAlive: true)
UserRepository userRepository(Ref ref) =>
    UserRepository(FirebaseFirestore.instance);

class UserRepository {
  final FirebaseFirestore _db;
  const UserRepository(this._db);

  CollectionReference<Map<String, dynamic>> get _users =>
      _db.collection('users');

  Future<void> createUser(AppUser user) =>
      _users.doc(user.uid).set(user.toJson());

  Future<AppUser> getUser(String uid) async {
    final snap = await _users.doc(uid).get();
    final data = snap.data();
    if (data == null) throw Exception('User not found');
    return AppUser.fromJson(data);
  }

  Future<void> updateProfile(String uid, UserProfileUpdate update) =>
      _users.doc(uid).update({
        'fullName': update.fullName,
        'department': update.department,
      });
}
