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
    
