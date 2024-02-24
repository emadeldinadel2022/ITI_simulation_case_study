select * from class;
select * from schedule;
select * from assignment;

INSERT ALL
INTO assignment (ASSIGNMENT_DESCRIPTION, ASSIGNMENT_TYPE, DEADLINE, CLASS_ID) VALUES ('lab 1', 'task', TO_TIMESTAMP('2024-01-26 23:59:59', 'YYYY-MM-DD HH24:MI:SS'), 5006)
INTO assignment (ASSIGNMENT_DESCRIPTION, ASSIGNMENT_TYPE, DEADLINE, CLASS_ID) VALUES ('lab 2', 'task', TO_TIMESTAMP('2024-02-02 23:59:59', 'YYYY-MM-DD HH24:MI:SS'), 5006)
INTO assignment (ASSIGNMENT_DESCRIPTION, ASSIGNMENT_TYPE, DEADLINE, CLASS_ID) VALUES ('project', 'project', TO_TIMESTAMP('2024-02-09 23:59:59', 'YYYY-MM-DD HH24:MI:SS'), 5006)
SELECT * FROM DUAL;

update assignment
set assignment_type = 'project'
where assignment_id = 10007;
