CREATE TABLE program (
    program_id NUMBER(6) PRIMARY KEY,
    program_name VARCHAR2(100) NOT NULL UNIQUE,
    program_description VARCHAR2(4000) NOT NULL,
    program_category VARCHAR2(50) NOT NULL CHECK (program_category IN ('9 months program', '3 months program')),
    program_establishment_date DATE NOT NULL,
    track_id NUMBER(4) NOT NULL,
    CONSTRAINT fk_track_program FOREIGN KEY (track_id) REFERENCES track(track_id)
);

CREATE SEQUENCE program_seq
    START WITH 1000
    INCREMENT BY 1
    NOMAXVALUE
    NOMINVALUE
    NOCYCLE
    NOCACHE;
    