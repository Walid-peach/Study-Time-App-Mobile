import 'package:cloud_firestore/cloud_firestore.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:riverpod_annotation/riverpod_annotation.dart';
import '../models/study_hall.dart';

part 'study_hall_repository.g.dart';

@Riverpod(keepAlive: true)
StudyHallRepository studyHallRepository(Ref ref) =>
    StudyHallRepository(FirebaseFirestore.instance);

class StudyHallRepository {
  final FirebaseFirestore _db;
  const StudyHallRepository(this._db);

  Future<List<StudyHall>> getHalls() async {
    final snap = await _db.collection('studyHalls').get();
    return snap.docs
        .map((d) => StudyHall.fromJson(d.data(), id: d.id))
        .toList();
  }

  Future<Set<int>> getBookedSlots({
    required String hallId,
    required int tableNumber,
    required int seatNumber,
    required String date,
  }) async {
    final snap = await _db
        .collection('bookings')
        .where('hallId', isEqualTo: hallId)
        .where('tableNumber', isEqualTo: tableNumber)
        .where('seatNumber', isEqualTo: seatNumber)
        .where('date', isEqualTo: date)
        .where('status', isEqualTo: 'active')
        .get();
    return snap.docs
        .map((d) => (d.data()['startHour'] as num).toInt())
        .toSet();
  }
}
