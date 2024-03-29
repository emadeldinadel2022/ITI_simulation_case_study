CREATE TABLE intake (
    intake_id VARCHAR2(100) PRIMARY KEY,
    start_date DATE NOT NULL,
    end_date DATE NOT NULL,
    program_id NUMBER(6),
    CONSTRAINT fk_program_intake FOREIGN KEY (program_id) REFERENCES program(program_id)
);

CREATE TABLE instructor_intake_grads (
    intake_id VARCHAR2(100),
    instructor_ssn CHAR(14),
    CONSTRAINT fk_intake_graduated FOREIGN KEY (intake_id) REFERENCES intake(intake_id),
    CONSTRAINT fk_instructor_graduated FOREIGN KEY (instructor_ssn) REFERENCES instructor(instructor_ssn),
    CONSTRAINT pk_graduated_from PRIMARY KEY (intake_id, instructor_ssn)
);


CREATE OR REPLACE FUNCTION generate_intake_id(
    p_program_name VARCHAR2,
    p_program_category VARCHAR2,
    p_start_date DATE,
    p_establishment_date DATE
) RETURN VARCHAR2
IS
    v_intake_id VARCHAR2(100);
    v_year_difference NUMBER;
BEGIN
    IF p_program_category = '9 months program' THEN
        v_year_difference := EXTRACT(YEAR FROM p_start_date) - EXTRACT(YEAR FROM p_establishment_date);
        v_intake_id := REPLACE(p_program_name, ' ', '_') || '_' || v_year_difference || '_9_P';
    ELSIF p_program_category = '3 months program' THEN
        v_intake_id := REPLACE(p_program_name, ' ', '_') || '_' || TO_CHAR(p_start_date, 'MM') || '_3_P';
    END IF;
    
    RETURN v_intake_id;
END;

-- Create Trigger for Insertion
CREATE OR REPLACE TRIGGER trg_insert_intake
BEFORE INSERT ON intake
FOR EACH ROW
DECLARE
    v_program_count NUMBER;
    v_date_count NUMBER;
    v_program_name program.program_name%TYPE;
    v_program_category program.program_category%TYPE;
    v_establishment_date program.program_establishment_date%TYPE;
BEGIN
    -- Check if program_id is not null and exists in the program table
    IF :NEW.program_id IS NULL THEN
        RAISE_APPLICATION_ERROR(-20001, 'Program ID cannot be NULL.');
    END IF;

    SELECT COUNT(*)
    INTO v_program_count
    FROM program
    WHERE program_id = :NEW.program_id;

    IF v_program_count = 0 THEN
        RAISE_APPLICATION_ERROR(-20002, 'Invalid program ID.');
    END IF;

    -- Check if start date and end date are different
    IF :NEW.start_date = :NEW.end_date THEN
        RAISE_APPLICATION_ERROR(-20003, 'Start date and end date cannot be the same.');
    END IF;

    SELECT program_name, program_category, program_establishment_date
    INTO v_program_name, v_program_category, v_establishment_date
    FROM program
    WHERE program_id = :NEW.program_id; 

    
    :NEW.intake_id := generate_intake_id(v_program_name, v_program_category, :NEW.start_date, v_establishment_date);
END;
/

-- Create Trigger for Update
CREATE OR REPLACE TRIGGER trg_update_intake
BEFORE UPDATE OF start_date, end_date, program_id ON intake
FOR EACH ROW
DECLARE
    v_program_count NUMBER;
    v_date_count NUMBER;
BEGIN

    SELECT COUNT(*)
    INTO v_program_count
    FROM program
    WHERE program_id = :NEW.program_id;

    IF v_program_count = 0 THEN
        RAISE_APPLICATION_ERROR(-20002, 'Invalid program ID.');
    END IF;

    -- Check if start date and end date are different
    IF :NEW.start_date = :NEW.end_date THEN
        RAISE_APPLICATION_ERROR(-20003, 'Start date and end date cannot be the same.');
    END IF;

    
END;
/

