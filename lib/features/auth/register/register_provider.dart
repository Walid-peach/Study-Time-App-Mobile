import 'package:riverpod_annotation/riverpod_annotation.dart';
import '../../../data/models/user.dart';
import '../../../data/repositories/auth_repository.dart';
import '../../../data/repositories/user_repository.dart';

part 'register_provider.g.dart';

@riverpod
class RegisterNotifier extends _$RegisterNotifier {
  @override
  AsyncValue<void> build() => const AsyncData(null);

  Future<void> register({
    required String fullName,
    required String email,
    required String password,
    required String department,
  }) async {
    state = const AsyncLoading();
    state = await AsyncValue.guard(() async {
      final firebaseUser =
          await ref.read(authRepositoryProvider).register(email, password);
      await ref.read(userRepositoryProvider).createUser(AppUser(
            uid: firebaseUser.uid,
            fullName: fullName,
            email: email,
            department: department,
          ));
    });
  }
}
