select * from student;
select * from schedule;

INSERT INTO attendance (student_ssn, schedule_id, ATTENDANCE_STATUS)
VALUES ('11111222221234', 20029, 0);

INSERT INTO attendance (student_ssn, schedule_id, ATTENDANCE_STATUS)
VALUES ('11111222221234', 20030, 1);

INSERT INTO attendance (student_ssn, schedule_id, ATTENDANCE_STATUS)
VALUES ('11111222221234', 20031, 1);
--------------------

INSERT INTO attendance (student_ssn, schedule_id, ATTENDANCE_STATUS)
VALUES ('11111222221235', 20029, 0);

INSERT INTO attendance (student_ssn, schedule_id, ATTENDANCE_STATUS)
VALUES ('11111222221235', 20030, 1);

INSERT INTO attendance (student_ssn, schedule_id, ATTENDANCE_STATUS)
VALUES ('11111222221235', 20031, 1);
-----------------------------

INSERT INTO attendance (student_ssn, schedule_id, ATTENDANCE_STATUS)
VALUES ('11111222221236', 20029, 0);

INSERT INTO attendance (student_ssn, schedule_id, ATTENDANCE_STATUS)
VALUES ('11111222221236', 20030, 1);

INSERT INTO attendance (student_ssn, schedule_id, ATTENDANCE_STATUS)
VALUES ('11111222221236', 20031, 1);

SELECT s.student_name, c.course_name, sch.day_of_week, sch.start_time, sch.end_time, a.attendance_status
FROM attendance a
JOIN student s ON a.student_ssn = s.student_ssn
JOIN schedule sch ON a.schedule_id = sch.schedule_id
JOIN class cls ON sch.class_id = cls.class_id
JOIN course c ON cls.course_code = c.course_code;
