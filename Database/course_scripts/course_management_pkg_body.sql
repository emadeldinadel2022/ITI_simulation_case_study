CREATE OR REPLACE PACKAGE BODY course_management_pkg AS
    PROCEDURE insert_course(
        course_name_in VARCHAR2,
        credit_hours_in NUMBER,
        course_syllabus_in VARCHAR2,
        course_description_in VARCHAR2
    ) AS
    BEGIN
        INSERT INTO course(course_code, course_name, credit_hours, course_syllabus, course_description)
        VALUES(course_code_seq.NEXTVAL, course_name_in, credit_hours_in, course_syllabus_in, course_description_in);
    END insert_course;

    PROCEDURE update_course(
        course_code_in NUMBER,
        course_name_in VARCHAR2 DEFAULT NULL,
        credit_hours_in NUMBER DEFAULT NULL,
        course_syllabus_in VARCHAR2 DEFAULT NULL,
        course_description_in VARCHAR2 DEFAULT NULL
    ) AS
    BEGIN
        UPDATE course
        SET course_name = NVL(course_name_in, course_name),
            credit_hours = NVL(credit_hours_in, credit_hours),
            course_syllabus = NVL(course_syllabus_in, course_syllabus),
            course_description = NVL(course_description_in, course_description)
        WHERE course_code = course_code_in;
    END update_course;
END course_management_pkg;
/