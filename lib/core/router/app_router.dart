import 'package:firebase_auth/firebase_auth.dart';
import 'package:flutter/material.dart';
import 'package:go_router/go_router.dart';

// Stub screens — replaced in later phases
class _StubScreen extends StatelessWidget {
  final String title;
  const _StubScreen(this.title);

  @override
  Widget build(BuildContext context) => Scaffold(
        appBar: AppBar(title: Text(title)),
        body: Center(child: Text(title)),
      );
}

final appRouter = GoRouter(
  initialLocation: '/login',
  redirect: (context, state) {
    final isLoggedIn = FirebaseAuth.instance.currentUser != null;
    final loc = state.matchedLocation;
    final isAuthRoute = loc.startsWith('/login') ||
        loc.startsWith('/register') ||
        loc.startsWith('/reset-password');
    if (!isLoggedIn && !isAuthRoute) return '/login';
    if (isLoggedIn && isAuthRoute) return '/dashboard';
    return null;
  },
  routes: [
    GoRoute(path: '/login', builder: (ctx, _) => const _StubScreen('Login')),
    GoRoute(path: '/register', builder: (ctx, _) => const _StubScreen('Register')),
    GoRoute(path: '/reset-password', builder: (ctx, _) => const _StubScreen('Reset Password')),
    GoRoute(path: '/dashboard', builder: (ctx, _) => const _StubScreen('Dashboard')),
    GoRoute(path: '/booking/halls', builder: (ctx, _) => const _StubScreen('Hall Selection')),
    GoRoute(path: '/booking/table', builder: (ctx, _) => const _StubScreen('Table Selection')),
    GoRoute(path: '/booking/time-slot', builder: (ctx, _) => const _StubScreen('Time Slot')),
    GoRoute(path: '/booking/confirm', builder: (ctx, _) => const _StubScreen('Confirmation')),
    GoRoute(path: '/my-bookings', builder: (ctx, _) => const _StubScreen('My Bookings')),
    GoRoute(path: '/profile', builder: (ctx, _) => const _StubScreen('Profile')),
  ],
);
