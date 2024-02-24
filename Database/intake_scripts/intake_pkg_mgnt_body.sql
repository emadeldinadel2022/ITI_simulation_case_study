-- Modify Package Body
CREATE OR REPLACE PACKAGE BODY intake_management_pkg AS
    PROCEDURE insert_intake(
        program_id_in NUMBER,
        start_date_in DATE,
        end_date_in DATE
    ) AS
    BEGIN
        INSERT INTO intake(intake_id, start_date, end_date, program_id)
        VALUES(NULL, start_date_in, end_date_in, program_id_in);
    END insert_intake;

    PROCEDURE update_intake(
        intake_id_in VARCHAR2,
        start_date_in DATE DEFAULT NULL,
        end_date_in DATE DEFAULT NULL,
        program_id_in NUMBER DEFAULT NULL
    ) AS
    BEGIN
        UPDATE intake
        SET start_date = NVL(start_date_in, start_date),
            end_date = NVL(end_date_in, end_date),
            program_id = NVL(program_id_in, program_id)
        WHERE intake_id = intake_id_in;
    END update_intake;
END intake_management_pkg;
/