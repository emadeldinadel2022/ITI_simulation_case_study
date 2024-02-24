CREATE OR REPLACE PACKAGE instructor_management_pkg AS

    -- Stored procedure for inserting instructors
    PROCEDURE insert_instructor(
        p_instructor_ssn IN CHAR,
        p_instructor_name IN VARCHAR2,
        p_instructor_email IN VARCHAR2,
        p_address IN VARCHAR2,
        p_phone IN NUMBER,
        p_date_of_birth IN DATE,
        p_track_id IN NUMBER
    );
    
    PROCEDURE update_instructor(
        p_instructor_ssn IN CHAR,
        p_instructor_email IN VARCHAR2,
        p_address IN VARCHAR2,
        p_phone IN NUMBER,
        p_track_supervision IN NUMBER
    );

END instructor_management_pkg;
/

