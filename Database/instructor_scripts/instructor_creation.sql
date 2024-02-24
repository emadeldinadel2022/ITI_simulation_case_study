CREATE TABLE instructor (
    instructor_ssn CHAR(14) PRIMARY KEY,
    instructor_name VARCHAR2(100) NOT NULL,
    instructor_email VARCHAR2(100) NOT NULL,
    address VARCHAR2(255) NOT NULL,
    phone NUMBER NOT NULL,
    date_of_birth DATE NOT NULL,
    instructor_password VARCHAR2(20),
    track_id NUMBER(4),
    CONSTRAINT fk_track_works_for FOREIGN KEY (track_id) REFERENCES track(track_id)
);

ALTER TABLE track ADD (
    supervisor_ssn CHAR(14),
    CONSTRAINT fk_track_supervisor FOREIGN KEY (supervisor_ssn) REFERENCES instructor(instructor_ssn)
);

ALTER TABLE instructor
ADD CONSTRAINT uq_instructor_email UNIQUE (instructor_email);

ALTER TABLE instructor
ADD CONSTRAINT uq_instructor_phone UNIQUE (phone);

ALTER TABLE instructor
ADD CONSTRAINT uq_instructor_name UNIQUE (instructor_name);

CREATE OR REPLACE TRIGGER instructor_insert_trigger
BEFORE INSERT ON instructor
FOR EACH ROW
DECLARE
    email_count NUMBER;
    name_count NUMBER;
    ssn_count NUMBER;
BEGIN
    SELECT COUNT(*) INTO ssn_count FROM instructor WHERE instructor_ssn = :NEW.instructor_ssn;
    IF ssn_count > 0 THEN
        RAISE_APPLICATION_ERROR(-20003, 'Instructor SSN already exists');
    END IF;
    
    -- Check if the email already exists
    SELECT COUNT(*) INTO email_count FROM instructor WHERE instructor_email = :NEW.instructor_email;
    IF email_count > 0 THEN
        RAISE_APPLICATION_ERROR(-20001, 'Email already exists');
    END IF;
    
    -- Check if the name already exists
    SELECT COUNT(*) INTO name_count FROM instructor WHERE instructor_name = :NEW.instructor_name;
    IF name_count > 0 THEN
        RAISE_APPLICATION_ERROR(-20002, 'Name already exists');
    END IF;

    instructor_management_pkg.insert_instructor(:NEW.instructor_ssn,
                                               :NEW.instructor_name,
                                               :NEW.instructor_email,
                                               :NEW.address,
                                               :NEW.phone,
                                               :NEW.date_of_birth, 
                                               :NEW.track_id);
END;
/

