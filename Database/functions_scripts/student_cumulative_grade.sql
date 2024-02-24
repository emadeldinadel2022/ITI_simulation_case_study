
select s.student_name, round(sum(c.credit_hours * fg.grade)/sum(c.credit_hours),2) as final_grade
from class_final_grade fg
join student s
on s.student_ssn = fg.student_ssn
join class cl
on fg.class_id = cl.class_id 
join course c
on c.course_code = cl.course_code
group by s.student_name;

CREATE OR REPLACE PROCEDURE update_student_grade IS
BEGIN
    FOR student_rec IN (
        SELECT s.student_ssn, round(sum(c.credit_hours * fg.grade) / sum(c.credit_hours), 2) AS final_grade
        FROM class_final_grade fg
        JOIN student s ON s.student_ssn = fg.student_ssn
        JOIN class cl ON fg.class_id = cl.class_id
        JOIN course c ON c.course_code = cl.course_code
        GROUP BY s.student_ssn
    ) LOOP
        DECLARE
            v_final_grade_code VARCHAR2(3);
        BEGIN
            -- Map the final grade to grade code
            SELECT grade_code INTO v_final_grade_code
            FROM grade
            WHERE student_rec.final_grade >= min_point AND student_rec.final_grade <= max_point;

            -- Update the cumulative grade in the student table
            UPDATE student
            SET cumulative_grade = v_final_grade_code
            WHERE student_ssn = student_rec.student_ssn;
        EXCEPTION
            WHEN NO_DATA_FOUND THEN
                NULL;
        END;
    END LOOP;

    COMMIT;
    DBMS_OUTPUT.PUT_LINE('Cumulative grades updated successfully.');
EXCEPTION
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Error: ' || SQLERRM);
        ROLLBACK;
END;
/


BEGIN
    update_student_grade;
END;
/

select * from student;
