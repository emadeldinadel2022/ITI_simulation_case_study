CREATE OR REPLACE PACKAGE instructor_management_pkg AS

  FUNCTION check_exp_course(
    in_course_code IN NUMBER,
    in_instructor_ssn IN CHAR,
    counter OUT NUMBER
  ) RETURN NUMBER;

  FUNCTION check_certification(
    in_qualification_id IN NUMBER,
    in_instructor_ssn IN CHAR,
    counter OUT NUMBER
  ) RETURN NUMBER;

  PROCEDURE add_qualification(
    in_qualification_id IN NUMBER,
    in_instructor_ssn IN CHAR
  );

  PROCEDURE add_exp_course(
    in_course_code IN NUMBER,
    in_instructor_ssn IN CHAR,
    in_years_of_exp IN NUMBER
  );

END instructor_management_pkg;

/

CREATE OR REPLACE PACKAGE BODY instructor_management_pkg AS


  FUNCTION check_exp_course(
    in_course_code IN NUMBER,
    in_instructor_ssn IN CHAR,
    counter OUT NUMBER
  ) RETURN NUMBER
  IS
  BEGIN
    SELECT COUNT(*)
      INTO counter
      FROM course_instructor
     WHERE course_code = in_course_code
       AND instructor_ssn = in_instructor_ssn;

    IF counter > 0 THEN
      RETURN 1;
    ELSE
      RETURN 0;
    END IF;
  END check_exp_course;

  FUNCTION check_certification(
    in_qualification_id IN NUMBER,
    in_instructor_ssn IN CHAR,
    counter OUT NUMBER
  ) RETURN NUMBER
  IS
  BEGIN
    SELECT COUNT(*)
      INTO counter
      FROM instructor_qualifications
     WHERE instructor_ssn = in_instructor_ssn
       AND qualification_id = in_qualification_id;

    IF counter > 0 THEN
      RETURN 1;
    ELSE
      RETURN 0;
    END IF;
  END check_certification;

  PROCEDURE add_qualification(
    in_qualification_id IN NUMBER,
    in_instructor_ssn IN CHAR
  )
  AS
  BEGIN
    INSERT INTO instructor_qualifications(qualification_id, instructor_ssn)
    VALUES(in_qualification_id, in_instructor_ssn);
  END add_qualification;

  PROCEDURE add_exp_course(
    in_course_code IN NUMBER,
    in_instructor_ssn IN CHAR,
    in_years_of_exp IN NUMBER
  )
  AS
  BEGIN
    INSERT INTO course_instructor(course_code, instructor_ssn, years_of_experience)
    VALUES(in_course_code, in_instructor_ssn, in_years_of_exp);
  END add_exp_course;

END instructor_management_pkg;

/

set serveroutput on;
declare
    v number;
begin
    v := instructor_management_pkg.check_certification(3003, '12345678901234', v);
    dbms_output.put_line(v);
end;