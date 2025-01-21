insert into user_info(id, pwd, name, gender, birth_year, birth_month)
VALUES ('admin', 'admin12345', '관리자', 'M', 2000, 12);

insert into user_info (birth_month, birth_year, gender, name, pwd, id)
values (1, 2025, 'M', 'test', 'testtest', 'test');

insert into user_info (birth_month, birth_year, gender, name, pwd, id, push_chk)
values (1, 2025, 'M', 'test2', 'testtest', 'test2', 0);

insert into user_info (birth_month, birth_year, gender, name, pwd, id, profile)
values (1, 2025, 'M', 'test3', 'testtest', 'test3', 'test_profile');

insert into quiz (title)
values ('테스트 퀴즈');

insert into quiz (title)
values ('테스트 퀴즈2');

insert into quiz (title)
values ('테스트 퀴즈3');

insert into user_quiz (quiz_id, user_id)
values (1, 'test');

insert into user_quiz (quiz_id, user_id, pass_yn)
values (2, 'test', 1);

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

insert into question (quiz_id, content, answer, choice1, choice2, choice3, choice4)
values (1, '테스트 퀴즈 내용', '정답', '정답', '오답', '오답', '오답');

insert into question (quiz_id, content, answer, choice1, choice2, choice3, choice4)
values (1, '테스트 퀴즈 내용2', '정답', '정답', '오답', '오답', '오답');

insert into question (quiz_id, content, answer, choice1, choice2, choice3, choice4)
values (1, '테스트 퀴즈 내용3', '정답', '정답', '오답', '오답', '오답');

insert into question (quiz_id, content, answer, choice1, choice2, choice3, choice4)
values (2, '테스트 퀴즈2 내용', '정답', '정답', '오답', '오답', '오답');

insert into question (quiz_id, content, answer, choice1, choice2, choice3, choice4)
values (3, '테스트 퀴즈3 내용', '정답', '정답', '오답', '오답', '오답');

insert into post (user_id, content, country, group_name, area_name, reaction)
VALUES ('test', '테스트 글', 'world', 'test group', 'test area', '라벨 없음');

insert into post (user_id, content, country, group_name, area_name, reaction)
VALUES ('test', '테스트 글2', 'world', 'test group', 'test area', '라벨 없음');

insert into post (user_id, content, country, group_name, area_name, reaction)
VALUES ('test', '테스트 글3', 'world', 'test group', 'test area', '라벨 없음');

insert into post (user_id, content, country, group_name, area_name, reaction)
VALUES ('test', '테스트 글4', 'world', 'test group', 'test area', '라벨 없음');

insert into post (user_id, content, country, group_name, area_name, reaction)
VALUES ('test', '테스트 글5', 'world', 'test group', 'test area', '라벨 없음');

insert into post (user_id, content, country, group_name, area_name, reaction)
VALUES ('test', '테스트 글6', 'world', 'test group', 'test area', '라벨 없음');

insert into post (user_id, content, country, group_name, area_name, reaction)
VALUES ('test', '테스트 글7', 'world', 'test group', 'test area', '라벨 없음');

insert into post (user_id, content, country, group_name, area_name, reaction)
VALUES ('test', '테스트 글8', 'world', 'test group', 'test area', '라벨 없음');

insert into post (user_id, content, country, group_name, area_name, reaction)
VALUES ('test', '테스트 글9', 'world', 'test group', 'test area', '라벨 없음');

insert into post (user_id, content, country, group_name, area_name, reaction)
VALUES ('test2', '테스트2 글', 'world', 'test group', 'test area', '라벨 없음');

insert into post (user_id, content, country, group_name, area_name, reaction)
VALUES ('test2', '테스트2 글2', 'world', 'test group', 'test area', '라벨 없음');

insert into post (user_id, content, country, group_name, area_name, reaction)
VALUES ('test2', '테스트2 글3', 'world', 'test group', 'test area', '라벨 없음');

insert into post (user_id, content, country, group_name, area_name, reaction)
VALUES ('test3', '테스트3 글', 'world', 'test group', 'test area', '라벨 없음');

insert into post (user_id, content, country, group_name, area_name, reaction)
VALUES ('admin', '테스트 공지', 'world', 'test group', 'test area', '라벨 없음');

insert into post (user_id, content, country, group_name, area_name, reaction)
VALUES ('admin', '테스트 공지2', 'world', 'test group', 'test area', '라벨 없음');

insert into reply (post_id, user_id, content)
VALUES ('1', 'test', '테스트 댓글');

insert into reply (post_id, user_id, content)
VALUES ('1', 'test', '테스트 댓글2');

insert into reply (post_id, user_id, content)
VALUES ('1', 'test', '테스트 댓글3');

insert into reply (post_id, user_id, content)
VALUES ('1', 'test2', '테스트2 댓글');

insert into reply (post_id, user_id, content)
VALUES ('1', 'test3', '테스트3 댓글');

insert into post_photo (post_id, origin_name, img_name, img_path)
VALUES (1, 'test_img', 'ttteeeesssstttiimmggg', 'img_path');

insert into post_photo (post_id, origin_name, img_name, img_path)
VALUES (1, 'test_img2', 'ttteeeesssstttiimmggg2', 'img_path2');

insert into post_like (post_id, user_id)
VALUES (1, 'test2');

insert into post_like (post_id, user_id)
VALUES (1, 'test3');