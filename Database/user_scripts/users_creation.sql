CREATE SEQUENCE user_id_seq
    START WITH 1
    INCREMENT BY 1
    NOMAXVALUE
    NOMINVALUE
    NOCYCLE
    NOCACHE;

CREATE TABLE users (
    user_id NUMBER PRIMARY KEY,
    password VARCHAR2(100) NOT NULL,
    role VARCHAR2(20) NOT NULL CHECK (role IN ('supervisor', 'instructor', 'student'))
);

CREATE OR REPLACE TRIGGER trg_insert_user
BEFORE INSERT ON users
FOR EACH ROW
BEGIN
    :NEW.user_id := user_id_seq.NEXTVAL;
END;

ALTER TABLE student
ADD user_id NUMBER;

ALTER TABLE instructor
ADD user_id NUMBER;

ALTER TABLE student
ADD CONSTRAINT fk_student_user
FOREIGN KEY (user_id)
REFERENCES users(user_id);

ALTER TABLE instructor
ADD CONSTRAINT fk_instructor_user
FOREIGN KEY (user_id)
REFERENCES users(user_id);

