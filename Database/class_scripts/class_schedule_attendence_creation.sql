CREATE SEQUENCE schedule_id_seq
    START WITH 20000
    INCREMENT BY 1
    NOMAXVALUE
    NOMINVALUE
    NOCYCLE
    NOCACHE;

CREATE TABLE schedule (
    schedule_id NUMBER PRIMARY KEY,
    day_of_week VARCHAR2(20) NOT NULL,
    start_time TIMESTAMP NOT NULL,
    end_time TIMESTAMP NOT NULL,
    class_id NUMBER,
    CONSTRAINT fk_schedule_class FOREIGN KEY (class_id) REFERENCES class(class_id)
);

CREATE TABLE attendance (
    student_ssn CHAR(14),
    schedule_id NUMBER,
    status NUMBER(1),
    CONSTRAINT pk_attendance PRIMARY KEY (student_ssn, schedule_id),
    CONSTRAINT fk_attendance_student FOREIGN KEY (student_ssn) REFERENCES student(student_ssn),
    CONSTRAINT fk_attendance_schedule FOREIGN KEY (schedule_id) REFERENCES schedule(schedule_id)
);


CREATE OR REPLACE TRIGGER trg_insert_assignment
BEFORE INSERT ON assignment
FOR EACH ROW
BEGIN
    :NEW.assignment_id:= assignment_id_seq.NEXTVAL;
END;