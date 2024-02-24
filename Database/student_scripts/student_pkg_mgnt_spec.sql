-- Create Package Body
CREATE OR REPLACE PACKAGE BODY student_management_pkg AS
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
    ) AS
    BEGIN
        INSERT INTO student(student_ssn, student_name, student_email, date_of_birth, phone_number, address, faculty, student_password, intake_id)
        VALUES(student_ssn_in, student_name_in, student_email_in, date_of_birth_in, phone_number_in, address_in, faculty_in, student_password_in, intake_id_in);
    END insert_student;

    PROCEDURE update_student(
        student_ssn_in CHAR,
        student_name_in VARCHAR2 DEFAULT NULL,
        student_email_in VARCHAR2 DEFAULT NULL,
        date_of_birth_in DATE DEFAULT NULL,
        phone_number_in VARCHAR2 DEFAULT NULL,
        address_in VARCHAR2 DEFAULT NULL,
        faculty_in VARCHAR2 DEFAULT NULL,
        student_password_in VARCHAR2 DEFAULT NULL
    ) AS
    BEGIN
        UPDATE student
        SET student_name = NVL(student_name_in, student_name),
            student_email = NVL(student_email_in, student_email),
            date_of_birth = NVL(date_of_birth_in, date_of_birth),
            phone_number = NVL(phone_number_in, phone_number),
            address = NVL(address_in, address),
            faculty = NVL(faculty_in, faculty),
            student_password = NVL(student_password_in, student_password)
        WHERE student_ssn = student_ssn_in;
    END update_student;
END student_management_pkg;
/