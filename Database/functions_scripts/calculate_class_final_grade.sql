select * from grade;

CREATE TABLE class_final_grade (
    CLASS_ID NUMBER,
    STUDENT_SSN CHAR(14 BYTE),
    GRADE NUMBER(5,2),
    CONSTRAINT fk_class_id FOREIGN KEY (CLASS_ID) REFERENCES class(CLASS_ID),
    CONSTRAINT fk_student_ssn FOREIGN KEY (STUDENT_SSN) REFERENCES student(STUDENT_SSN),
    CONSTRAINT pk_class_final_grade PRIMARY KEY (CLASS_ID, STUDENT_SSN)
);


DROP TABLE CLASS_FINAL_GRADE;


SELECT TEST_PERCENTAGE, ASSIGNMENT_PERCENTAGE, PROJECT_PERCENTAGE
    FROM class
    WHERE CLASS_ID = 5000;
    

SELECT GRADE_POINT * 0.3
    FROM class_student_test
    WHERE STUDENT_SSN = '11111222221234'
    AND CLASS_ID = 5000;
    
SELECT AVG(GRADE_POINT) * 0.3
    FROM assignment_student_grade
    WHERE STUDENT_SSN = '11111222221234'
    AND ASSIGNMENT_ID IN (
        SELECT ASSIGNMENT_ID
        FROM assignment
        WHERE CLASS_ID = 5000
        and ASSIGNMENT_TYPE = 'task'
    );


SELECT GRADE_POINT * 0.4
    FROM assignment_student_grade
    WHERE STUDENT_SSN = '11111222221234'
    AND ASSIGNMENT_ID IN (
        SELECT ASSIGNMENT_ID
        FROM assignment
        WHERE CLASS_ID = 5000
        AND ASSIGNMENT_TYPE = 'project'
    );


CREATE OR REPLACE FUNCTION calculate_final_grades(p_student_ssn IN CHAR, p_class_id IN NUMBER) RETURN NUMBER AS
    v_test_percentage NUMBER;
    v_assignment_percentage NUMBER;
    v_project_percentage NUMBER;
    v_project_grade NUMBER;
    v_assignments_grade NUMBER;
    v_test_grade NUMBER;
    v_total_grade NUMBER;
BEGIN
    -- Get percentages from class table
    SELECT TEST_PERCENTAGE, ASSIGNMENT_PERCENTAGE, PROJECT_PERCENTAGE
    INTO v_test_percentage, v_assignment_percentage, v_project_percentage
    FROM class
    WHERE CLASS_ID = p_class_id;

    -- Calculate grade for tests
    SELECT GRADE_POINT * v_test_percentage 
    INTO v_test_grade
    FROM class_student_test
    WHERE STUDENT_SSN = p_student_ssn
    AND CLASS_ID = p_class_id;


    -- Calculate grade for assignments
    SELECT AVG(GRADE_POINT) * v_assignment_percentage 
    INTO v_assignments_grade
    FROM assignment_student_grade
    WHERE STUDENT_SSN = p_student_ssn
    AND ASSIGNMENT_ID IN (
        SELECT ASSIGNMENT_ID
        FROM assignment
        WHERE CLASS_ID = p_class_id
        AND ASSIGNMENT_TYPE = 'task'
    );


    -- Calculate grade for project
    SELECT GRADE_POINT * v_project_percentage 
    INTO v_project_grade
    FROM assignment_student_grade
    WHERE STUDENT_SSN = p_student_ssn
    AND ASSIGNMENT_ID IN (
        SELECT ASSIGNMENT_ID
        FROM assignment
        WHERE CLASS_ID = p_class_id
        AND ASSIGNMENT_TYPE = 'project'
    );
    

    
    v_total_grade := NVL(v_assignments_grade, 0) + NVU(v_test_grade, 0) + NVL(v_project_grade, 0);
    

    RETURN v_total_grade;
END;
/


    SELECT grade_code
    INTO v_final_grade
    FROM grade
    WHERE v_total_grade >= min_point AND v_total_grade <= max_point;

select calculate_final_grades('11111222221236', 5001) from dual;

set SERVEROUTPUT on;
BEGIN
    FOR class_rec IN (SELECT DISTINCT CLASS_ID FROM class where class_id not in (select class_id from class_final_grade)) LOOP
        FOR student_rec IN (SELECT DISTINCT STUDENT_SSN FROM class_student_test WHERE CLASS_ID = class_rec.CLASS_ID) LOOP
            DBMS_OUTPUT.PUT_LINE(calculate_final_grades(student_rec.STUDENT_SSN, class_rec.CLASS_ID));
        END LOOP;
    END LOOP;
END;
SELECT DISTINCT CLASS_ID FROM class where class_id not in (select class_id from class_final_grade);
SELECT DISTINCT STUDENT_SSN FROM class_student_test WHERE CLASS_ID = class_rec.CLASS_ID;

CREATE OR REPLACE PROCEDURE insert_final_grades IS
BEGIN
    FOR class_rec IN (SELECT DISTINCT CLASS_ID FROM class where class_id not in (select class_id from class_final_grade)) LOOP
        FOR student_rec IN (SELECT DISTINCT STUDENT_SSN FROM class_student_test WHERE CLASS_ID = class_rec.CLASS_ID) LOOP
            INSERT INTO class_final_grade (CLASS_ID, STUDENT_SSN, GRADE)
            VALUES (class_rec.CLASS_ID, student_rec.STUDENT_SSN, calculate_final_grades(student_rec.STUDENT_SSN, class_rec.CLASS_ID));
        END LOOP;
    END LOOP;
    COMMIT;
    DBMS_OUTPUT.PUT_LINE('Final grades inserted successfully.');
EXCEPTION
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Error: ' || SQLERRM);
        ROLLBACK;
END;
/

BEGIN
    insert_final_grades;
END;
/

select * from class_final_grade;

select * from class;


