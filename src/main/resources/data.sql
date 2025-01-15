insert into user_info (birth_month, birth_year, gender, name, pwd, id)
values (1, 2025, 'M', 'test', 'testtest', 'test');

insert into user_info (birth_month, birth_year, gender, name, pwd, id)
values (1, 2025, 'M', 'test2', 'testtest', 'test2');

insert into user_info (birth_month, birth_year, gender, name, pwd, id)
values (1, 2025, 'M', 'test3', 'testtest', 'test3');

insert into quiz (title)
values ('테스트 퀴즈');

insert into quiz (title)
values ('테스트 퀴즈2');

insert into quiz (title)
values ('테스트 퀴즈3');

insert into user_quiz (quiz_id, user_id)
values (1, 'test');

insert into user_quiz (quiz_id, user_id)
values (2, 'test');

insert into user_quiz (quiz_id, user_id)
values (3, 'test');

insert into user_quiz (quiz_id, user_id)
values (2, 'test2');

insert into user_quiz (quiz_id, user_id)
values (3, 'test3');

insert into history (end_date, start_date, title, user_id)
values ('2024-12-01', '2024-11-01', '테스트 봉사기록', 'test');

insert into history (end_date, start_date, title, user_id)
values ('2024-12-01', '2024-11-01', '테스트 봉사기록2', 'test2');

insert into history (end_date, start_date, title, user_id)
values ('2024-12-01', '2024-11-01', '테스트 봉사기록3', 'test3');