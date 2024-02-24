
CREATE OR REPLACE PACKAGE BODY instructor_management_pkg AS

    -- Stored procedure for inserting instructors
    PROCEDURE insert_instructor(
        p_instructor_ssn IN CHAR,
        p_instructor_name IN VARCHAR2,
        p_instructor_email IN VARCHAR2,
        p_address IN VARCHAR2,
        p_phone IN NUMBER,
        p_date_of_birth IN DATE,
        p_track_id IN NUMBER
    ) AS
    
        v_track_id_exists NUMBER(4);
        v_track_id NUMBER(4);
    BEGIN
    
        SELECT COUNT(*)
        INTO v_track_id_exists
        FROM track
        where track_id = p_track_id;
        
        IF v_track_id_exists = 0 THEN
            v_track_id := null;
        END IF;
        
        -- Check constraint: Instructor SSN length must be 14 characters
        IF LENGTH(p_instructor_ssn) != 14 THEN
            RAISE_APPLICATION_ERROR(-20002, 'Instructor SSN must be 14 characters');
        END IF;

        -- Check constraint: Instructor email must be in a valid format
        IF INSTR(p_instructor_email, '@') = 0 OR INSTR(p_instructor_email, '.') = 0 THEN
            RAISE_APPLICATION_ERROR(-20003, 'Invalid email format');
        END IF;

        -- Inserting the instructor
        INSERT INTO instructor (instructor_ssn, instructor_name, instructor_email, address, phone, date_of_birth, track_id)
        VALUES (p_instructor_ssn, p_instructor_name, p_instructor_email, p_address, p_phone, p_date_of_birth,  v_track_id);

        COMMIT;
    EXCEPTION
        WHEN OTHERS THEN
            ROLLBACK;
            RAISE;
    END insert_instructor;
    
    -- Procedure for updating instructor information
    PROCEDURE update_instructor(
        p_instructor_ssn IN CHAR,
        p_instructor_email IN VARCHAR2,
        p_address IN VARCHAR2,
        p_phone IN NUMBER,
        p_track_supervision IN NUMBER
    ) AS
    BEGIN
        -- Start the update statement
        UPDATE instructor
        SET
            -- Check if the email parameter is not null, then update
            instructor_email = NVL(p_instructor_email, instructor_email),
            -- Check if the address parameter is not null, then update
            address = NVL(p_address, address),
            -- Check if the phone parameter is not null, then update
            phone = NVL(p_phone, phone),
            -- Check if the track_supervision parameter is not null, then update
            track_id = NVL(p_track_supervision, track_supervision)
        WHERE instructor_ssn = p_instructor_ssn;
        
        COMMIT;
    EXCEPTION
        WHEN OTHERS THEN
            ROLLBACK;
            RAISE;
    END update_instructor;

END instructor_management_pkg;
/
