import 'package:firebase_core/firebase_core.dart';
import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'core/router/app_router.dart';
import 'core/theme/app_theme.dart';
import 'firebase_options.dart';

void main() async {
  WidgetsFlutterBinding.ensureInitialized();
  await Firebase.initializeApp(options: DefaultFirebaseOptions.currentPlatform);
  runApp(const ProviderScope(child: StudyTimeApp()));
}

class StudyTimeApp extends ConsumerWidget {
  const StudyTimeApp({super.key});

  @override
  Widget build(BuildContext context, WidgetRef ref) => MaterialApp.router(
        title: 'StudyTime',
        theme: AppTheme.light,
        routerConfig: ref.watch(appRouterProvider),
        debugShowCheckedModeBanner: false,
      );
}
