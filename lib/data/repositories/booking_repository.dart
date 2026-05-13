import 'package:cloud_firestore/cloud_firestore.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:riverpod_annotation/riverpod_annotation.dart';
import '../models/booking.dart';

part 'booking_repository.g.dart';

@Riverpod(keepAlive: true)
BookingRepository bookingRepository(Ref ref) =>
    BookingRepository(FirebaseFirestore.instance);

class BookingRepository {
  final FirebaseFirestore _db;
  const BookingRepository(this._db);

  CollectionReference<Map<String, dynamic>> get _bookings =>
      _db.collection('bookings');

  // Writes the booking and slot-lock atomically — prevents double-booking.
  Future<String> createBooking(Booking booking) async {
    final slotKey =
        '${booking.hallId}_t${booking.tableNumber}_s${booking.seatNumber}_${booking.date}_h${booking.startHour}';
    final slotRef = _db.collection('bookedSlots').doc(slotKey);
    final bookingRef = _bookings.doc();
    final data = booking.copyWith(createdAt: Timestamp.now()).toJson();

    await _db.runTransaction((tx) async {
      final slotSnap = await tx.get(slotRef);
      if (slotSnap.exists) {
        throw Exception('This seat and time slot is already booked.');
      }
      tx.set(slotRef,
          {'bookingId': bookingRef.id, 'createdAt': Timestamp.now()});
      tx.set(bookingRef, data);
    });

    return bookingRef.id;
  }

  Future<List<Booking>> getUserBookings(String userId) async {
    final snap = await _bookings
        .where('userId', isEqualTo: userId)
        .orderBy('createdAt', descending: true)
        .get();
    return snap.docs
        .map((d) => Booking.fromJson(d.data(), id: d.id))
        .toList();
  }

  Future<void> cancelBooking(String bookingId) async {
    final bookingRef = _bookings.doc(bookingId);

    await _db.runTransaction((tx) async {
      final snap = await tx.get(bookingRef);
      final data = snap.data();
      if (data == null) throw Exception('Booking not found');

      final booking = Booking.fromJson(data, id: bookingId);
      if (booking.status == Booking.statusCancelled) {
        throw Exception('Booking is already cancelled.');
      }

      final slotKey =
          '${booking.hallId}_t${booking.tableNumber}_s${booking.seatNumber}_${booking.date}_h${booking.startHour}';
      final slotRef = _db.collection('bookedSlots').doc(slotKey);

      tx.update(bookingRef, {'status': Booking.statusCancelled});
      tx.delete(slotRef);
    });
  }
}
