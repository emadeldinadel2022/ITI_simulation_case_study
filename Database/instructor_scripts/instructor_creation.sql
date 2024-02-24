CREATE TABLE instructor (
    instructor_ssn CHAR(14) PRIMARY KEY,
    instructor_name VARCHAR2(100) NOT NULL,
    instructor_email VARCHAR2(100) NOT NULL,
    address VARCHAR2(255) NOT NULL,
    phone NUMBER NOT NULL,
    date_of_birth DATE NOT NULL,
    track_id NUMBER(4),
    CONSTRAINT fk_track_works_for FOREIGN KEY (track_id) REFERENCES track(track_id)
);

ALTER TABLE track ADD (
    supervisor_ssn CHAR(14),
    CONSTRAINT fk_track_supervisor FOREIGN KEY (supervisor_ssn) REFERENCES instructor(instructor_ssn)
);