class StudyHall {
  final String id;
  final String name;
  final String location;
  final int capacity;
  final int tableCount;
  final String imageUrl;

  const StudyHall({
    this.id = '',
    this.name = '',
    this.location = '',
    this.capacity = 0,
    this.tableCount = 10,
    this.imageUrl = '',
  });

  factory StudyHall.fromJson(Map<String, dynamic> json, {String id = ''}) => StudyHall(
        id: id,
        name: json['name'] as String? ?? '',
        location: json['location'] as String? ?? '',
        capacity: (json['capacity'] as num?)?.toInt() ?? 0,
        tableCount: (json['tableCount'] as num?)?.toInt() ?? 10,
        imageUrl: json['imageUrl'] as String? ?? '',
      );

  Map<String, dynamic> toJson() => {
        'name': name,
        'location': location,
        'capacity': capacity,
        'tableCount': tableCount,
        'imageUrl': imageUrl,
      };
}
