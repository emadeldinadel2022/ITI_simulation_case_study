select * from program;
select * from track;


CREATE OR REPLACE VIEW track_program_view AS
SELECT 
    track.*, 
    program.program_id, 
    program.program_name, 
    program.program_description, 
    program.program_category,
    program.program_establishment_date,
    instructor.instructor_name
FROM 
    track, 
    program,
    instructor
WHERE 
    track.track_id = program.track_id
    and track.supervisor_ssn = instructor.instructor_ssn;
    
select * from student;


CREATE VIEW course_program_view AS
select course.*, program.program_id
from course_program
join course
on course.course_code = course_program.course_code
join program
on program.program_id = course_program.program_id;

create or replace view instructor_qualification_view as
select i.instructor_ssn, q.qualification_id, q.qualification_name 
from qualifications q
join instructor_qualifications iq
on iq.qualification_id = q.qualification_id
join instructor i
on i.instructor_ssn = iq.instructor_ssn;

select qualification_id, qualification_name
from qualifications;

select * from instructor_qualification_view
WHERE instructor_ssn != '12345678901234';

select*from instructor;


create or replace view course_instructor_view as
select i.instructor_ssn, c.course_code, c.course_name, ic.years_of_experience
from course_instructor ic
join instructor i
on ic.instructor_ssn = i.instructor_ssn
join course c
on c.course_code = ic.course_code;

select * from  COURSE_INSTRUCTOR_VIEW
where instructor_ssn = '12345678901234';

select * from instructor;