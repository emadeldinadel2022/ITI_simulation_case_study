CREATE OR REPLACE PACKAGE track_management_pkg AS

    -- Procedure for inserting a track
    PROCEDURE insert_track(
        p_track_name IN VARCHAR2,
        p_track_description IN VARCHAR2,
        p_business_field IN VARCHAR2,
        p_establishment_date IN DATE,
        p_supervision_ssn IN CHAR
    );

    -- Procedure for updating a track
    PROCEDURE update_track(
        p_track_id IN NUMBER,
        p_track_name IN VARCHAR2,
        p_track_description IN VARCHAR2,
        p_business_field IN VARCHAR2 ,
        p_establishment_date IN DATE,
        p_supervision_ssn IN CHAR
    );

END track_management_pkg;
/