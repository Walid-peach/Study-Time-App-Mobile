import 'package:flutter/material.dart';
import 'package:google_fonts/google_fonts.dart';

class AppColors {
  static const brandBlue = Color(0xFF2563EB);
  static const brandBlueDark = Color(0xFF1D4ED8);
  static const brandBlueLight = Color(0xFFDBEAFE);
  static const brandAmber = Color(0xFFF59E0B);
  static const surface = Color(0xFFF8F9FA);
  static const slotAvailable = Color(0xFFE8F5E9);
  static const slotSelected = Color(0xFF1565C0);
  static const slotBooked = Color(0xFFFFCDD2);
  static const statusActive = Color(0xFFC8E6C9);
  static const statusCancelled = Color(0xFFFFCDD2);
}

class AppTheme {
  static ThemeData get light => ThemeData(
        useMaterial3: true,
        colorScheme: ColorScheme.fromSeed(
          seedColor: AppColors.brandBlue,
          primary: AppColors.brandBlue,
          secondary: AppColors.brandAmber,
          surface: AppColors.surface,
        ),
        scaffoldBackgroundColor: AppColors.surface,
        appBarTheme: AppBarTheme(
          backgroundColor: AppColors.brandBlue,
          foregroundColor: Colors.white,
          elevation: 0,
          titleTextStyle: GoogleFonts.inter(
            color: Colors.white,
            fontSize: 18,
            fontWeight: FontWeight.w600,
          ),
        ),
        textTheme: GoogleFonts.interTextTheme(),
        elevatedButtonTheme: ElevatedButtonThemeData(
          style: ElevatedButton.styleFrom(
            backgroundColor: AppColors.brandBlue,
            foregroundColor: Colors.white,
            minimumSize: const Size.fromHeight(48),
            shape: RoundedRectangleBorder(
              borderRadius: BorderRadius.circular(8),
            ),
          ),
        ),
        inputDecorationTheme: InputDecorationTheme(
          border: OutlineInputBorder(borderRadius: BorderRadius.circular(8)),
          filled: true,
          fillColor: Colors.white,
        ),
      );
}
