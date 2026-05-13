import 'package:riverpod_annotation/riverpod_annotation.dart';
import '../../../data/repositories/auth_repository.dart';

part 'reset_password_provider.g.dart';

@riverpod
class ResetPasswordNotifier extends _$ResetPasswordNotifier {
  @override
  AsyncValue<void> build() => const AsyncData(null);

  Future<void> sendPasswordReset(String email) async {
    state = const AsyncLoading();
    state = await AsyncValue.guard(
      () => ref.read(authRepositoryProvider).sendPasswordReset(email),
    );
  }
}
