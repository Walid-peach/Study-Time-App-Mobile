class TimeSlot {
  final String id;
  final String label;
  final int startHour;
  final int startMinute;
  final int durationMinutes;
  final bool isBooked;

  const TimeSlot({
    this.id = '',
    this.label = '',
    this.startHour = 0,
    this.startMinute = 0,
    this.durationMinutes = 60,
    this.isBooked = false,
  });

  TimeSlot copyWith({bool? isBooked}) => TimeSlot(
        id: id,
        label: label,
        startHour: startHour,
        startMinute: startMinute,
        durationMinutes: durationMinutes,
        isBooked: isBooked ?? this.isBooked,
      );

  static List<TimeSlot> generateDay() {
    return List.generate(14, (i) {
      final hour = 8 + i;
      final suffix = hour < 12 ? 'AM' : 'PM';
      final display = hour % 12 == 0 ? 12 : hour % 12;
      return TimeSlot(
        id: 'slot_$hour',
        label: '$display:00 $suffix',
        startHour: hour,
      );
    });
  }
}
