CREATE OR REPLACE PACKAGE program_management_pkg AS
    PROCEDURE insert_program(
        program_name_in IN VARCHAR2,
        program_description_in IN VARCHAR2,
        program_category_in IN VARCHAR2,
        program_establishment_date_in IN DATE,
        track_id_in IN NUMBER
    );
    
    PROCEDURE update_program(
        program_id_in IN NUMBER,
        program_name_in IN VARCHAR2 DEFAULT NULL,
        program_description_in IN VARCHAR2 DEFAULT NULL,
        program_establishment_date_in IN DATE DEFAULT NULL
    );
END;
/