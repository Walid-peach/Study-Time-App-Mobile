import 'package:firebase_auth/firebase_auth.dart';
import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:go_router/go_router.dart';
import 'package:riverpod_annotation/riverpod_annotation.dart';

part 'router_notifier.g.dart';

@riverpod
Stream<User?> authStateChanges(Ref ref) =>
    FirebaseAuth.instance.authStateChanges();

// Bridges the Riverpod auth stream into a Listenable GoRouter can watch.
class RouterNotifier extends ChangeNotifier {
  RouterNotifier(this._ref) {
    _ref.listen<AsyncValue<User?>>(authStateChangesProvider, (prev, next) {
      notifyListeners();
    });
  }

  final Ref _ref;

  String? redirect(BuildContext context, GoRouterState state) {
    final authAsync = _ref.read(authStateChangesProvider);
    // Don't redirect while stream hasn't emitted yet.
    final isLoggedIn = authAsync.valueOrNull != null;
    final loc = state.matchedLocation;
    final isAuthRoute = loc.startsWith('/login') ||
        loc.startsWith('/register') ||
        loc.startsWith('/reset-password');

    if (!isLoggedIn && !isAuthRoute) return '/login';
    if (isLoggedIn && isAuthRoute) return '/dashboard';
    return null;
  }
}
