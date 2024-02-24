CREATE SEQUENCE job_id_seq
    START WITH 500
    INCREMENT BY 1
    NOMAXVALUE
    NOMINVALUE
    NOCYCLE
    NOCACHE;

CREATE TABLE job_profile (
    job_id NUMBER PRIMARY KEY,
    job_profile_name VARCHAR2(100) NOT NULL UNIQUE,
    job_description VARCHAR2(255) NOT NULL
);

CREATE OR REPLACE TRIGGER trg_insert_job_profile
BEFORE INSERT ON job_profile
FOR EACH ROW
DECLARE 
    v_count_job_profile NUMBER;
BEGIN
    SELECT COUNT(*)
    INTO v_count_job_profile
    FROM job_profile 
    WHERE job_profile_name = :NEW.job_profile_name;

    IF v_count_job_profile > 0 THEN
        RAISE_APPLICATION_ERROR(-20002, 'Job profile name must be unique.');
    END IF;
    
    :NEW.job_id := job_id_seq.NEXTVAL;
END;
/


CREATE TABLE job_profile_program (
    job_id NUMBER,
    program_id NUMBER,
    CONSTRAINT pk_job_profile_program PRIMARY KEY (job_id, program_id),
    CONSTRAINT fk_job_profile_job FOREIGN KEY (job_id) REFERENCES job_profile(job_id),
    CONSTRAINT fk_job_profile_program FOREIGN KEY (program_id) REFERENCES program(program_id)
);



CREATE SEQUENCE qualification_id_seq
    START WITH 3000
    INCREMENT BY 1
    NOMAXVALUE
    NOMINVALUE
    NOCYCLE
    NOCACHE;

CREATE TABLE qualifications (
    qualification_id NUMBER PRIMARY KEY,
    qualification_name VARCHAR2(100) NOT NULL UNIQUE,
    qualification_description VARCHAR2(255) NOT NULL
);

CREATE TABLE instructor_qualifications (
    instructor_ssn CHAR(14),
    qualification_id NUMBER,
    CONSTRAINT pk_instructor_qualifications PRIMARY KEY (instructor_ssn, qualification_id),
    CONSTRAINT fk_ins_qual_instructor FOREIGN KEY (instructor_ssn) REFERENCES instructor(instructor_ssn),
    CONSTRAINT fk_ins_qual_qualification FOREIGN KEY (qualification_id) REFERENCES qualifications(qualification_id)
);

CREATE OR REPLACE TRIGGER trg_insert_qualifications
BEFORE INSERT ON qualifications
FOR EACH ROW
DECLARE
    v_count NUMBER;
BEGIN
    SELECT COUNT(*) INTO v_count
    FROM qualifications
    WHERE qualification_name = :NEW.qualification_name;

    IF v_count > 0 THEN
        RAISE_APPLICATION_ERROR(-20001, 'Qualification name must be unique.');
    END IF;

    :NEW.qualification_id := qualification_id_seq.NEXTVAL;
END;
/
