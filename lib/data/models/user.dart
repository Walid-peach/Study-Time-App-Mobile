class AppUser {
  final String uid;
  final String fullName;
  final String email;
  final String department;

  const AppUser({
    this.uid = '',
    this.fullName = '',
    this.email = '',
    this.department = '',
  });

  factory AppUser.fromJson(Map<String, dynamic> json) => AppUser(
        uid: json['uid'] as String? ?? '',
        fullName: json['fullName'] as String? ?? '',
        email: json['email'] as String? ?? '',
        department: json['department'] as String? ?? '',
      );

  Map<String, dynamic> toJson() => {
        'uid': uid,
        'fullName': fullName,
        'email': email,
        'department': department,
      };
}
