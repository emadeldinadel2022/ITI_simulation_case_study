INSERT INTO class_student_test (CLASS_ID, STUDENT_SSN, GRADE_POINT)
SELECT 5006, '11111222221234', ROUND(DBMS_RANDOM.VALUE(0.0, 4.0), 1) FROM DUAL UNION ALL
SELECT 5006, '11111222221235', ROUND(DBMS_RANDOM.VALUE(0.0, 4.0), 1) FROM DUAL UNION ALL
SELECT 5006, '11111222221236', ROUND(DBMS_RANDOM.VALUE(0.0, 4.0), 1) FROM DUAL;


select * from class_student_test;