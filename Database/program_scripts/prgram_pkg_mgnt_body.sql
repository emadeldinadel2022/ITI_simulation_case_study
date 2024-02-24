CREATE OR REPLACE PACKAGE BODY program_management_pkg AS
    PROCEDURE insert_program(
        program_name_in IN VARCHAR2,
        program_description_in IN VARCHAR2,
        program_category_in IN VARCHAR2,
        program_establishment_date_in IN DATE,
        track_id_in IN NUMBER
    ) AS
    BEGIN
        INSERT INTO program(program_id, program_name, program_description, program_category, program_establishment_date, track_id)
        VALUES(program_seq.NEXTVAL, program_name_in, program_description_in, program_category_in, program_establishment_date_in, track_id_in);
    END insert_program;

    PROCEDURE update_program(
        program_id_in IN NUMBER,
        program_name_in IN VARCHAR2 DEFAULT NULL,
        program_description_in IN VARCHAR2 DEFAULT NULL,
        program_establishment_date_in IN DATE DEFAULT NULL
    ) AS
    BEGIN
        UPDATE program
        SET program_name = NVL(program_name_in, program_name),
            program_description = NVL(program_description_in, program_description),
            program_establishment_date = NVL(program_establishment_date_in, program_establishment_date)
        WHERE program_id = program_id_in;
    END update_program;
END program_management_pkg;
/