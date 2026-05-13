import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:go_router/go_router.dart';
import 'package:riverpod_annotation/riverpod_annotation.dart';
import '../../features/auth/login/login_screen.dart';
import '../../features/auth/register/register_screen.dart';
import '../../features/auth/reset_password/reset_password_screen.dart';
import 'router_notifier.dart';

part 'app_router.g.dart';

class _StubScreen extends StatelessWidget {
  final String title;
  const _StubScreen(this.title);

  @override
  Widget build(BuildContext context) => Scaffold(
        appBar: AppBar(title: Text(title)),
        body: Center(child: Text(title)),
      );
}

@riverpod
GoRouter appRouter(Ref ref) {
  final notifier = RouterNotifier(ref);
  return GoRouter(
    initialLocation: '/login',
    refreshListenable: notifier,
    redirect: notifier.redirect,
    routes: [
      GoRoute(path: '/login', builder: (ctx, _) => const LoginScreen()),
      GoRoute(path: '/register', builder: (ctx, _) => const RegisterScreen()),
      GoRoute(path: '/reset-password', builder: (ctx, _) => const ResetPasswordScreen()),
      GoRoute(path: '/dashboard', builder: (ctx, _) => const _StubScreen('Dashboard')),
      GoRoute(path: '/booking/halls', builder: (ctx, _) => const _StubScreen('Hall Selection')),
      GoRoute(path: '/booking/table', builder: (ctx, _) => const _StubScreen('Table Selection')),
      GoRoute(path: '/booking/time-slot', builder: (ctx, _) => const _StubScreen('Time Slot')),
      GoRoute(path: '/booking/confirm', builder: (ctx, _) => const _StubScreen('Confirmation')),
      GoRoute(path: '/my-bookings', builder: (ctx, _) => const _StubScreen('My Bookings')),
      GoRoute(path: '/profile', builder: (ctx, _) => const _StubScreen('Profile')),
    ],
  );
}
