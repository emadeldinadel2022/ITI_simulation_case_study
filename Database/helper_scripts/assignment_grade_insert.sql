select * from student;

select * from grade;

INSERT INTO assignment_student_grade (ASSIGNMENT_ID, STUDENT_SSN, GRADE_POINT)
SELECT 10017, '11111222221234', ROUND(DBMS_RANDOM.VALUE(0.0, 4.0), 1) FROM DUAL UNION ALL
SELECT 10018, '11111222221234', ROUND(DBMS_RANDOM.VALUE(0.0, 4.0), 1) FROM DUAL UNION ALL
SELECT 10019, '11111222221234', ROUND(DBMS_RANDOM.VALUE(0.0, 4.0), 1) FROM DUAL UNION ALL
SELECT 10017, '11111222221235', ROUND(DBMS_RANDOM.VALUE(0.0, 4.0), 1) FROM DUAL UNION ALL
SELECT 10018, '11111222221235', ROUND(DBMS_RANDOM.VALUE(0.0, 4.0), 1) FROM DUAL UNION ALL
SELECT 10019, '11111222221235', ROUND(DBMS_RANDOM.VALUE(0.0, 4.0), 1) FROM DUAL UNION ALL
SELECT 10017, '11111222221236', ROUND(DBMS_RANDOM.VALUE(0.0, 4.0), 1) FROM DUAL UNION ALL
SELECT 10018, '11111222221236', ROUND(DBMS_RANDOM.VALUE(0.0, 4.0), 1) FROM DUAL UNION ALL
SELECT 10019, '11111222221236', ROUND(DBMS_RANDOM.VALUE(0.0, 4.0), 1) FROM DUAL ;

select * from assignment_student_grade;
