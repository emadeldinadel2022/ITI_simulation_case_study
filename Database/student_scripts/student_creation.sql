CREATE TABLE student (
    student_ssn CHAR(14) PRIMARY KEY,
    student_name VARCHAR2(100) NOT NULL UNIQUE,
    student_email VARCHAR2(100) NOT NULL UNIQUE,
    date_of_birth DATE NOT NULL,
    phone_number VARCHAR2(20) NOT NULL,
    address VARCHAR2(255) NOT NULL,
    faculty VARCHAR2(100) NOT NULL,
    student_password VARCHAR2(20) NOT NULL,
    intake_id VARCHAR2(100),
    CONSTRAINT fk_intake_student FOREIGN KEY (intake_id) REFERENCES intake(intake_id)
);

ALTER TABLE student
ADD CONSTRAINT uq_phone_number UNIQUE (phone_number);

ALTER TABLE student
ADD (cumulative_grade VARCHAR2(3) DEFAULT 'N/A');

-- Create Trigger for Insertion
CREATE OR REPLACE TRIGGER trg_insert_student
BEFORE INSERT ON student
FOR EACH ROW
DECLARE
    v_email_count NUMBER;
    v_phone_count NUMBER;
    v_name_count NUMBER;
    v_intake_count NUMBER;
BEGIN

    IF :NEW.date_of_birth > SYSDATE THEN
        RAISE_APPLICATION_ERROR(-20004, 'Date of birth cannot be in the future.');
    END IF;

    SELECT COUNT(*) INTO v_name_count FROM student WHERE student_name = :NEW.student_name;
    IF v_name_count > 0 THEN
        RAISE_APPLICATION_ERROR(-20005, 'Student name must be unique.');
    END IF;

    SELECT COUNT(*) INTO v_email_count FROM student WHERE student_email = :NEW.student_email;
    IF v_email_count > 0 THEN
        RAISE_APPLICATION_ERROR(-20006, 'Student email must be unique.');
    END IF;
    
    SELECT COUNT(*) INTO v_phone_count FROM student WHERE phone_number = :NEW.phone_number;
    IF v_phone_count > 0 THEN
        RAISE_APPLICATION_ERROR(-20007, 'Phone number must be unique.');
    END IF;

    SELECT COUNT(*) INTO v_intake_count FROM intake WHERE intake_id = :NEW.intake_id;
    IF v_intake_count = 0 THEN
        RAISE_APPLICATION_ERROR(-20008, 'Intake ID does not exist.');
    END IF;
END;
/

-- Create Trigger for Update
CREATE OR REPLACE TRIGGER trg_update_student
BEFORE UPDATE ON student
FOR EACH ROW
DECLARE
    v_email_count NUMBER;
    v_phone_count NUMBER;
    v_name_count NUMBER;
BEGIN
    
    IF :NEW.date_of_birth > SYSDATE THEN
        RAISE_APPLICATION_ERROR(-20012, 'Date of birth cannot be in the future.');
    END IF;

    SELECT COUNT(*) INTO v_name_count FROM student WHERE student_name = :NEW.student_name AND student_ssn != :NEW.student_ssn;
    IF v_name_count > 0 THEN
        RAISE_APPLICATION_ERROR(-20013, 'Student name must be unique.');
    END IF;

    SELECT COUNT(*) INTO v_email_count FROM student WHERE student_email = :NEW.student_email AND student_ssn != :NEW.student_ssn;
    IF v_email_count > 0 THEN
        RAISE_APPLICATION_ERROR(-20014, 'Student email must be unique.');
    END IF;
    
    SELECT COUNT(*) INTO v_phone_count FROM student WHERE phone_number = :NEW.phone_number AND student_ssn != :NEW.student_ssn;
    IF v_phone_count > 0 THEN
        RAISE_APPLICATION_ERROR(-20015, 'Phone number must be unique.');
    END IF;
END;
/
