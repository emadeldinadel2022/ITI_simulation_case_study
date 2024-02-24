CREATE TABLE track (
    track_id NUMBER(4) PRIMARY KEY,
    track_name VARCHAR2(100) UNIQUE NOT NULL,
    track_description VARCHAR2(4000) NOT NULL,
    business_field VARCHAR2(100) NOT NULL CHECK (business_field IN ('content developments', 'industrial systems', 'information system', 'infrastructure and security', 'software engineering')),
    establishment_date DATE NOT NULL
);

CREATE SEQUENCE track_seq
    START WITH 100
    INCREMENT BY 1
    NOMAXVALUE
    NOMINVALUE
    NOCYCLE
    NOCACHE;
    
CREATE OR REPLACE TRIGGER track_insert_update_trigger
BEFORE INSERT OR UPDATE ON track
FOR EACH ROW
DECLARE
    track_count NUMBER;
    supervisor_count NUMBER;
BEGIN

    IF INSERTING OR :NEW.track_name != :OLD.track_name THEN

        IF :NEW.track_name IS NOT NULL THEN
            SELECT COUNT(*)
            INTO track_count
            FROM track
            WHERE track_name = :NEW.track_name
              AND track_id != NVL(:NEW.track_id, -1); 
            
            IF track_count > 0 THEN
                RAISE_APPLICATION_ERROR(-20001, 'Track name already exists');
            END IF;
        END IF;
    END IF;


    IF :NEW.supervisor_ssn IS NOT NULL THEN
        SELECT COUNT(*)
        INTO supervisor_count
        FROM instructor
        WHERE instructor_ssn = :NEW.supervisor_ssn;

        IF supervisor_count = 0 THEN
            RAISE_APPLICATION_ERROR(-20002, 'Supervisor SSN is not valid or does not exist');
        END IF;
        
        SELECT COUNT(*)
        INTO supervisor_count
        FROM track
        WHERE supervisor_ssn = :NEW.supervisor_ssn;
        
        IF supervisor_count > 0 THEN
            RAISE_APPLICATION_ERROR(-20003, 'Supervisor is exist with another track');
        END IF;
        
        IF LENGTH(:NEW.supervisor_ssn) != 14 THEN
            RAISE_APPLICATION_ERROR(-20003, 'Supervisor SSN must be 14 characters long');
        END IF;
    END IF;
END;
/


