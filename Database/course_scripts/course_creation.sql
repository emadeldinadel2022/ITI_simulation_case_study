CREATE SEQUENCE course_code_seq
    START WITH 2000
    INCREMENT BY 1
    NOMAXVALUE
    NOMINVALUE
    NOCYCLE
    NOCACHE;

CREATE TABLE course (
    course_code NUMBER PRIMARY KEY,
    course_name VARCHAR2(100) UNIQUE NOT NULL,
    credit_hours NUMBER NOT NULL,
    course_syllabus VARCHAR2(1000) NOT NULL,
    course_description VARCHAR2(1000) NOT NULL
);

CREATE TABLE course_program (
    course_code NUMBER,
    program_id NUMBER(6),
    CONSTRAINT pk_course_program PRIMARY KEY (course_code, program_id),
    CONSTRAINT fk_course_program_course FOREIGN KEY (course_code) REFERENCES course(course_code),
    CONSTRAINT fk_course_program_program FOREIGN KEY (program_id) REFERENCES program(program_id)
);

CREATE TABLE course_instructor (
    course_code NUMBER,
    instructor_ssn CHAR(14),
    years_of_experience NUMBER(2),
    CONSTRAINT pk_course_instructor PRIMARY KEY (course_code, instructor_ssn),
    CONSTRAINT fk_course_instructor FOREIGN KEY (course_code) REFERENCES course(course_code),
    CONSTRAINT fk_instructor_course FOREIGN KEY (instructor_ssn) REFERENCES instructor(instructor_ssn)
);

CREATE TABLE course_prerequisite (
    course_code NUMBER,
    prerequisite_code NUMBER,
    CONSTRAINT pk_course_prerequisite PRIMARY KEY (course_code, prerequisite_code),
    CONSTRAINT fk_course_prerequisite FOREIGN KEY (course_code) REFERENCES course(course_code),
    CONSTRAINT fk_prerequisite_course FOREIGN KEY (prerequisite_code) REFERENCES course(course_code)
);

CREATE OR REPLACE TRIGGER trg_insert_course
BEFORE INSERT ON course
FOR EACH ROW
BEGIN
    :NEW.course_code := course_code_seq.NEXTVAL;
END;

-- Create Trigger for Insertion
CREATE OR REPLACE TRIGGER trg_insert_course
BEFORE INSERT ON course
FOR EACH ROW
DECLARE
    v_course_count NUMBER;
BEGIN
    IF :NEW.course_name IS NULL THEN
        RAISE_APPLICATION_ERROR(-20001, 'Course name cannot be NULL.');
    END IF;

    SELECT COUNT(*)
    INTO v_course_count
    FROM course
    WHERE course_name = :NEW.course_name;

    IF v_course_count > 0 THEN
        RAISE_APPLICATION_ERROR(-20002, 'Course name must be unique.');
    END IF;
END;
/

-- Create Trigger for Update
CREATE OR REPLACE TRIGGER trg_update_course
BEFORE UPDATE OF course_name, credit_hours, course_syllabus, course_description ON course
FOR EACH ROW
DECLARE
    v_course_count NUMBER;
BEGIN

    SELECT COUNT(*)
    INTO v_course_count
    FROM course
    WHERE course_name = :NEW.course_name
    AND course_code != :NEW.course_code;

    IF v_course_count > 0 THEN
        RAISE_APPLICATION_ERROR(-20004, 'Course name must be unique.');
    END IF;
END;
/
