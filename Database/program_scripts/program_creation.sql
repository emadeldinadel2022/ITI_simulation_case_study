CREATE TABLE program (
    program_id NUMBER(6) PRIMARY KEY,
    program_name VARCHAR2(100) NOT NULL UNIQUE,
    program_description VARCHAR2(4000) NOT NULL,
    program_category VARCHAR2(50) NOT NULL CHECK (program_category IN ('9 months program', '3 months program')),
    program_establishment_date DATE NOT NULL,
    track_id NUMBER(4) NOT NULL,
    CONSTRAINT fk_track_program FOREIGN KEY (track_id) REFERENCES track(track_id)
);

CREATE SEQUENCE program_seq
    START WITH 1000
    INCREMENT BY 1
    NOMAXVALUE
    NOMINVALUE
    NOCYCLE
    NOCACHE;


-- Create Trigger for Insertion
CREATE OR REPLACE TRIGGER trg_check_program_insert
BEFORE INSERT ON program
FOR EACH ROW
DECLARE
    v_program_id NUMBER;
    v_track_id NUMBER;
BEGIN
    -- Check uniqueness of program name
    SELECT COUNT(*) INTO v_program_id
    FROM program
    WHERE program_name = :NEW.program_name;

    IF v_program_id > 0 THEN
        RAISE_APPLICATION_ERROR(-20001, 'Program name must be unique.');
    END IF;

    -- Check establishment date
    IF :NEW.program_establishment_date > SYSDATE THEN
        RAISE_APPLICATION_ERROR(-20002, 'Establishment date cannot be in the future.');
    END IF;

    -- Check if track_id exists in track table
    SELECT COUNT(*) INTO v_track_id
    FROM track
    WHERE track_id = :NEW.track_id;

    IF v_track_id = 0 THEN
        RAISE_APPLICATION_ERROR(-20003, 'Invalid track ID.');
    END IF;
    
END;
/    

-- Create Trigger for Update
CREATE OR REPLACE TRIGGER trg_check_program_update
BEFORE UPDATE OF program_name, program_description, program_establishment_date ON program
FOR EACH ROW
DECLARE
    v_program_count NUMBER;
    v_track_count NUMBER;
BEGIN
    -- Check establishment date
    IF :NEW.program_establishment_date > SYSDATE THEN
        RAISE_APPLICATION_ERROR(-20002, 'Establishment date cannot be in the future.');
    END IF;

    -- Check if track_id exists in track table
    IF :NEW.track_id IS NOT NULL THEN
        SELECT COUNT(*) INTO v_track_count
        FROM track
        WHERE track_id = :NEW.track_id;

        IF v_track_count = 0 THEN
            RAISE_APPLICATION_ERROR(-20003, 'Invalid track ID.');
        END IF;
    END IF;

    -- Check uniqueness of program name
    SELECT COUNT(*) INTO v_program_count
    FROM program
    WHERE program_name = :NEW.program_name;
      

    IF v_program_count > 0 THEN
        RAISE_APPLICATION_ERROR(-20004, 'Program name must be unique.');
    END IF;
END;
/
