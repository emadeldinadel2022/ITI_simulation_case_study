CREATE TABLE grade (
    grade_code VARCHAR2(3) PRIMARY KEY,
    points NUMBER UNIQUE NOT NULL
);

INSERT INTO grade (grade_code, points) VALUES ('A+', 4.0);
INSERT INTO grade (grade_code, points) VALUES ('A', 3.7);
INSERT INTO grade (grade_code, points) VALUES ('A-', 3.5);
INSERT INTO grade (grade_code, points) VALUES ('B+', 3.3);
INSERT INTO grade (grade_code, points) VALUES ('B', 3.0);
INSERT INTO grade (grade_code, points) VALUES ('B-', 2.7);
INSERT INTO grade (grade_code, points) VALUES ('C+', 2.3);
INSERT INTO grade (grade_code, points) VALUES ('C', 2.0);
INSERT INTO grade (grade_code, points) VALUES ('C-', 1.7);
INSERT INTO grade (grade_code, points) VALUES ('D+', 1.3);
INSERT INTO grade (grade_code, points) VALUES ('D', 1.0);
INSERT INTO grade (grade_code, points) VALUES ('D-', 0.7);
INSERT INTO grade (grade_code, points) VALUES ('F', 0.0);

