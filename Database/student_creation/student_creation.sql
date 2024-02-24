CREATE TABLE student (
    student_ssn CHAR(14) PRIMARY KEY,
    student_name VARCHAR2(100) NOT NULL UNIQUE,
    student_email VARCHAR2(100) NOT NULL UNIQUE,
    date_of_birth DATE NOT NULL,
    phone_number CHAR(13) NOT NULL,
    address VARCHAR2(255) NOT NULL,
    faculty VARCHAR2(100) NOT NULL,
    intake_id VARCHAR2(15),
    CONSTRAINT fk_intake_student FOREIGN KEY (intake_id) REFERENCES intake(intake_id)
);
