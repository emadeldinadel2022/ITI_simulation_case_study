create or replace view final_grades_view as
select s.student_ssn, s.student_name, c.course_name, cl.class_id, i.instructor_name, cfg.grade as final_grade
from class_final_grade cfg
join student s
on s.student_ssn = cfg.student_ssn
join class cl
on cl.class_id = cfg.class_id
join course c
on cl.course_code = c.course_code
join instructor i
on i.instructor_ssn = cl.instructor_ssn
order by s.student_name;


create or replace view test_grades_view as
select s.student_ssn, s.student_name, c.course_name, cl.class_id, i.instructor_name, cst.grade_point as tesst_grade
from class_student_test cst
join student s
on s.student_ssn = cst.student_ssn
join class cl 
on cl.class_id = cst.class_id
join course c
on cl.course_code = c.course_code
join instructor i
on i.instructor_ssn = cl.instructor_ssn;

create or replace view project_grades_view as
select s.student_ssn, s.student_name, c.course_name, cl.class_id, i.instructor_name, asg.grade_point as project_grade
from assignment_student_grade asg
join student s
on s.student_ssn = asg.student_ssn
join assignment ast
on ast.assignment_id = asg.assignment_id
join class cl
on cl.class_id = ast.class_id
join course c
on c.course_code = cl.course_code
join instructor i
on i.instructor_ssn = cl.instructor_ssn
where ast.assignment_type = 'project';


CREATE VIEW student_attendance_percentage AS
SELECT 
    s.student_name,
    a.student_ssn,
    cl.class_id,
    ROUND((SUM(CASE WHEN a.attendance_status = 1 THEN 1 ELSE 0 END) / COUNT(a.student_ssn)) * 100, 2) AS attendance_percentage
FROM 
    attendance a
JOIN 
    student s ON a.student_ssn = s.student_ssn
JOIN
    schedule sh
ON
    sh.schedule_id = a.schedule_id
join 
    class cl
on
    cl.class_id = sh.class_id
GROUP BY 
    s.student_name, a.student_ssn, cl.class_id
ORDER BY s.student_name;

create or replace view assignment_grades_view as
select s.student_ssn, s.student_name, cl.class_id, sum(asg.grade_point) as assignment_grade
from assignment_student_grade asg
join student s
on s.student_ssn = asg.student_ssn
join assignment ast
on ast.assignment_id = asg.assignment_id
join class cl
on cl.class_id = ast.class_id
where ast.assignment_type = 'task'
group by s.student_ssn, s.student_name, cl.class_id
order by student_name;

create or replace view student_report as 
select fgv.student_ssn, fgv.student_name, fgv.course_name, fgv.class_id, fgv.instructor_name,
    fgv.final_grade, tgv.tesst_grade as test_grade, pgv.project_grade, agv.assignment_grade, sap.attendance_percentage
from final_grades_view fgv
join  test_grades_view tgv
on fgv.student_ssn = tgv.student_ssn
and fgv.class_id = tgv.class_id
join project_grades_view pgv
on fgv.student_ssn = pgv.student_ssn
and fgv.class_id = pgv.class_id
join assignment_grades_view agv
on fgv.student_ssn = agv.student_ssn
and fgv.class_id = agv.class_id
join student_attendance_percentage sap
on fgv.student_ssn = sap.student_ssn
and fgv.class_id = sap.class_id
order by fgv.student_name;



select * from student_report;

create or replace view student_instructor_fg_report as
select i.instructor_ssn, fgv.*, p.program_name
from final_grades_view fgv
join instructor i
on i.instructor_name = fgv.instructor_name
join student s
on s.student_ssn = fgv.student_ssn
join intake 
on s.intake_id = intake.intake_id
join program p
on p.program_id = intake.program_id;

select * from student_instructor_fg_report;

create or replace view avg_grades_per_instructor as
select instructor_ssn, instructor_name, program_name, course_name, class_id,
    round(avg(final_grade), 2) as avg_grades
from student_instructor_fg_report
group by instructor_ssn, instructor_name, program_name, class_id,course_name
order by instructor_name;

select * from avg_grades_per_instructor;

SELECT * FROM student_report;