CREATE OR REPLACE PACKAGE student_management_pkg AS
    PROCEDURE insert_student(
        student_ssn_in CHAR,
        student_name_in VARCHAR2,
        student_email_in VARCHAR2,
        date_of_birth_in DATE,
        phone_number_in VARCHAR2,
        address_in VARCHAR2,
        faculty_in VARCHAR2,
        student_password_in VARCHAR2,
        intake_id_in VARCHAR2
    );

    PROCEDURE update_student(
        student_ssn_in CHAR,
        student_name_in VARCHAR2 DEFAULT NULL,
        student_email_in VARCHAR2 DEFAULT NULL,
        date_of_birth_in DATE DEFAULT NULL,
        phone_number_in VARCHAR2 DEFAULT NULL,
        address_in VARCHAR2 DEFAULT NULL,
        faculty_in VARCHAR2 DEFAULT NULL,
        student_password_in VARCHAR2 DEFAULT NULL
    );
END;
/
