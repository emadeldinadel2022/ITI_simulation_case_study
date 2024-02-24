CREATE OR REPLACE PACKAGE BODY track_management_pkg AS

    -- Procedure for inserting a track
    PROCEDURE insert_track(
        p_track_name IN VARCHAR2,
        p_track_description IN VARCHAR2,
        p_business_field IN VARCHAR2,
        p_establishment_date IN DATE,
        p_supervision_ssn IN CHAR
    ) AS
    BEGIN
        INSERT INTO track (track_id, track_name, track_description, business_field, establishment_date, supervisor_ssn)
        VALUES (track_seq.NEXTVAL, p_track_name, p_track_description, p_business_field, p_establishment_date, p_supervision_ssn);
        
        COMMIT;
    EXCEPTION
        WHEN OTHERS THEN
            ROLLBACK;
            RAISE;
    END insert_track;

    -- Procedure for updating a track
    PROCEDURE update_track(
        p_track_id IN NUMBER,
        p_track_name IN VARCHAR2,
        p_track_description IN VARCHAR2,
        p_business_field IN VARCHAR2,
        p_establishment_date IN DATE,
        p_supervision_ssn IN CHAR
    ) AS
    BEGIN
        UPDATE track
        SET
            track_name = NVL(p_track_name, track_name),
            track_description = NVL(p_track_description, track_description),
            business_field = NVL(p_business_field, business_field),
            establishment_date = NVL(p_establishment_date, establishment_date),
            supervisor_ssn = p_supervision_ssn
        WHERE track_id = p_track_id;
        
        COMMIT;
    EXCEPTION
        WHEN OTHERS THEN
            ROLLBACK;
            RAISE;
    END update_track;

END track_management_pkg;
