import 'package:cloud_firestore/cloud_firestore.dart';

class Booking {
  static const statusActive = 'active';
  static const statusCancelled = 'cancelled';

  final String id;
  final String userId;
  final String hallId;
  final String hallName;
  final int tableNumber;
  final int seatNumber;
  final String date;
  final String timeSlotLabel;
  final int startHour;
  final int durationMinutes;
  final Timestamp? createdAt;
  final String status;

  const Booking({
    this.id = '',
    this.userId = '',
    this.hallId = '',
    this.hallName = '',
    this.tableNumber = 0,
    this.seatNumber = 0,
    this.date = '',
    this.timeSlotLabel = '',
    this.startHour = 0,
    this.durationMinutes = 60,
    this.createdAt,
    this.status = statusActive,
  });

  factory Booking.fromJson(Map<String, dynamic> json, {String id = ''}) => Booking(
        id: id,
        userId: json['userId'] as String? ?? '',
        hallId: json['hallId'] as String? ?? '',
        hallName: json['hallName'] as String? ?? '',
        tableNumber: (json['tableNumber'] as num?)?.toInt() ?? 0,
        seatNumber: (json['seatNumber'] as num?)?.toInt() ?? 0,
        date: json['date'] as String? ?? '',
        timeSlotLabel: json['timeSlotLabel'] as String? ?? '',
        startHour: (json['startHour'] as num?)?.toInt() ?? 0,
        durationMinutes: (json['durationMinutes'] as num?)?.toInt() ?? 60,
        createdAt: json['createdAt'] as Timestamp?,
        status: json['status'] as String? ?? statusActive,
      );

  Map<String, dynamic> toJson() => {
        'userId': userId,
        'hallId': hallId,
        'hallName': hallName,
        'tableNumber': tableNumber,
        'seatNumber': seatNumber,
        'date': date,
        'timeSlotLabel': timeSlotLabel,
        'startHour': startHour,
        'durationMinutes': durationMinutes,
        'createdAt': createdAt,
        'status': status,
      };

  Booking copyWith({String? id, Timestamp? createdAt, String? status}) => Booking(
        id: id ?? this.id,
        userId: userId,
        hallId: hallId,
        hallName: hallName,
        tableNumber: tableNumber,
        seatNumber: seatNumber,
        date: date,
        timeSlotLabel: timeSlotLabel,
        startHour: startHour,
        durationMinutes: durationMinutes,
        createdAt: createdAt ?? this.createdAt,
        status: status ?? this.status,
      );
}
