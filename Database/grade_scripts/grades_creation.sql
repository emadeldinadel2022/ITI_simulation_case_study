CREATE TABLE grade (
    grade_code VARCHAR2(3) PRIMARY KEY,
    max_point NUMBER UNIQUE NOT NULL,
    min_point NUMBER UNIQUE NOT NULL
);

INSERT INTO grade (grade_code, max_point, min_point) VALUES ('A+', 4.0, 3.7);
INSERT INTO grade (grade_code, max_point, min_point) VALUES ('A', 3.69, 3.5);
INSERT INTO grade (grade_code, max_point, min_point) VALUES ('A-', 3.49, 3.3);
INSERT INTO grade (grade_code, max_point, min_point) VALUES ('B+', 3.29, 3.0);
INSERT INTO grade (grade_code, max_point, min_point) VALUES ('B', 2.99, 2.7);
INSERT INTO grade (grade_code, max_point, min_point) VALUES ('B-', 2.69, 2.3);
INSERT INTO grade (grade_code, max_point, min_point) VALUES ('C+', 2.29, 2.0);
INSERT INTO grade (grade_code, max_point, min_point) VALUES ('C', 1.99, 1.7);
INSERT INTO grade (grade_code, max_point, min_point) VALUES ('C-', 1.69, 1.3);
INSERT INTO grade (grade_code, max_point, min_point) VALUES ('D', 1.29, 1.0);
INSERT INTO grade (grade_code, max_point, min_point) VALUES ('F', 0.9, 0.0);

