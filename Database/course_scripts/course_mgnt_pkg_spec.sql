CREATE OR REPLACE PACKAGE course_management_pkg AS
    PROCEDURE insert_course(
        course_name_in VARCHAR2,
        credit_hours_in NUMBER,
        course_syllabus_in VARCHAR2,
        course_description_in VARCHAR2
    );
    
    PROCEDURE update_course(
        course_code_in NUMBER,
        course_name_in VARCHAR2 DEFAULT NULL,
        credit_hours_in NUMBER DEFAULT NULL,
        course_syllabus_in VARCHAR2 DEFAULT NULL,
        course_description_in VARCHAR2 DEFAULT NULL
    );
END;
/