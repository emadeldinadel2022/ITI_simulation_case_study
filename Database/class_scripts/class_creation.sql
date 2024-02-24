CREATE SEQUENCE class_id_seq
    START WITH 5000
    INCREMENT BY 1
    NOMAXVALUE
    NOMINVALUE
    NOCYCLE
    NOCACHE;

CREATE TABLE class (
    class_id NUMBER PRIMARY KEY,
    class_type VARCHAR2(50) NOT NULL CHECK (class_type IN ('online', 'offline')),
    start_date DATE NOT NULL,
    end_date DATE NOT NULL,
    project_percentage NUMBER DEFAULT 0.0,
    test_percentage NUMBER DEFAULT 0.0,
    assignment_percentage NUMBER DEFAULT 0.0,
    instructor_ssn CHAR(14),
    course_code NUMBER,
    intake_id VARCHAR2(100),
    CONSTRAINT fk_class_instructor FOREIGN KEY (instructor_ssn) REFERENCES instructor(instructor_ssn),
    CONSTRAINT fk_class_course FOREIGN KEY (course_code) REFERENCES course(course_code),
    CONSTRAINT fk_class_intake FOREIGN KEY (intake_id) REFERENCES intake(intake_id)
);

CREATE OR REPLACE TRIGGER trg_insert_class
BEFORE INSERT ON class
FOR EACH ROW
BEGIN
    :NEW.class_id := class_id_seq.NEXTVAL;
END;

