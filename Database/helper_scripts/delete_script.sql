CREATE OR REPLACE PROCEDURE delete_course_procedure(course_code IN NUMBER)
IS
BEGIN
    DELETE FROM course_program WHERE course_code = course_code;
    DELETE FROM course_prerequisite WHERE course_code = course_code;
    DELETE FROM course_instructor WHERE course_code = course_code;
    DELETE FROM assignment WHERE course_code = course_code;
    DELETE FROM class WHERE course_code = course_code;
    DELETE FROM course WHERE course_code = course_code;
END;
/

CREATE OR REPLACE TRIGGER delete_course_cascade
BEFORE DELETE ON course
FOR EACH ROW
BEGIN
    delete_course_procedure(:OLD.COURSE_CODE);
END;
/

CREATE OR REPLACE PROCEDURE delete_instructor_procedure(instructor_ssn IN CHAR)
IS
BEGIN
    DELETE instructor_intake_grads WHERE instructor_ssn = instructor_ssn;
    DELETE FROM instructor_qualifications WHERE instructor_ssn = instructor_ssn;
    DELETE FROM instructor WHERE instructor_ssn = instructor_ssn;
END;
/


CREATE OR REPLACE TRIGGER delete_instructor_cascade
BEFORE DELETE ON instructor
FOR EACH ROW
BEGIN
    delete_instructor_procedure(:OLD.INSTRUCTOR_SSN);
END;
/




CREATE OR REPLACE PROCEDURE delete_student_procedure(student_ssn IN CHAR)
IS
BEGIN
    DELETE FROM attendance WHERE student_ssn = student_ssn;
    DELETE FROM class_student_test WHERE student_ssn = student_ssn;
    DELETE FROM class_final_grade WHERE student_ssn = student_ssn;
    DELETE FROM assignment_student_grade WHERE student_ssn = student_ssn;
    DELETE FROM student WHERE student_ssn = student_ssn;
END;
/

CREATE OR REPLACE TRIGGER delete_student_cascade
BEFORE DELETE ON student
FOR EACH ROW
BEGIN
    delete_student_procedure(:OLD.STUDENT_SSN);
END;
/

