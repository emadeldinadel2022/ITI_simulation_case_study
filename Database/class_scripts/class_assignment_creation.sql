CREATE SEQUENCE assignment_id_seq
    START WITH 10000
    INCREMENT BY 1
    NOMAXVALUE
    NOMINVALUE
    NOCYCLE
    NOCACHE;
    
CREATE TABLE assignment (
    assignment_id NUMBER PRIMARY KEY,
    assignment_description VARCHAR2(255) NOT NULL,
    assignment_type VARCHAR2(50) NOT NULL CHECK (assignment_type IN ('project', 'task')),
    deadline TIMESTAMP NOT NULL,
    class_id number,
    CONSTRAINT fk_assignment_class FOREIGN KEY (class_id) REFERENCES class(class_id)
);

CREATE OR REPLACE TRIGGER trg_insert_assignment
BEFORE INSERT ON assignment
FOR EACH ROW
BEGIN
    :NEW.assignment_id:= assignment_id_seq.NEXTVAL;
END;


CREATE TABLE assignment_student_grade (
    assignment_id NUMBER,
    student_ssn CHAR(14),
    grade_code VARCHAR2(3),
    CONSTRAINT pk_assignment_student_grade PRIMARY KEY (assignment_id, student_ssn),
    CONSTRAINT fk_assignment_grade FOREIGN KEY (assignment_id) REFERENCES assignment(assignment_id),
    CONSTRAINT fk_student_grade FOREIGN KEY (student_ssn) REFERENCES student(student_ssn),
    CONSTRAINT fk_student_assign_grade FOREIGN KEY (grade_code) REFERENCES grade(grade_code)
);

CREATE TABLE class_student_test (
    class_id NUMBER,
    student_ssn CHAR(14),
    grade_code VARCHAR2(3),
    CONSTRAINT pk_class_student_test PRIMARY KEY (class_id, student_ssn),
    CONSTRAINT fk_class_test FOREIGN KEY (class_id) REFERENCES class(class_id),
    CONSTRAINT fk_student_test FOREIGN KEY (student_ssn) REFERENCES student(student_ssn),
    CONSTRAINT fk_student_test_grade FOREIGN KEY (grade_code) REFERENCES grade(grade_code)
);
