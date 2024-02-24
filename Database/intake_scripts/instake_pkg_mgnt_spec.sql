-- Modify Package
CREATE OR REPLACE PACKAGE intake_management_pkg AS
    PROCEDURE insert_intake(
        program_id_in NUMBER,
        start_date_in DATE,
        end_date_in DATE
    );
    
    PROCEDURE update_intake(
        intake_id_in VARCHAR2,
        start_date_in DATE DEFAULT NULL,
        end_date_in DATE DEFAULT NULL,
        program_id_in NUMBER DEFAULT NULL
    );
END;
/
