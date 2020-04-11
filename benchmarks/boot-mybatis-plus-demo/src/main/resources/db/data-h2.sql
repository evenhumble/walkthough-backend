DELETE FROM user;

INSERT INTO user (id, name, age, email) VALUES
(1, 'Jone', 18, 'test1@baomidou.com'),
(2, 'Jack', 20, 'test2@baomidou.com'),
(3, 'Tom', 28, 'test3@baomidou.com'),
(4, 'Sandy', 21, 'test4@baomidou.com'),
(5, 'Billie', 24, 'test5@baomidou.com');

delete from project;
insert into project(id,project_name,project_summary,parent_id) values
(1,'p1','project p1',null),
(2,'p2','project p2',null),
(3,'p3','project p3',null),
(4,'p4','project p4',2);


DELETE FROM big_user_2018;

INSERT INTO big_user_2018 (id, name, age, email) VALUES
(1, 'Jone', 18, 'test1@baomidou.com');

DELETE FROM big_user_2019;

INSERT INTO big_user_2019 (id, name, age, email) VALUES
(1, 'Jack', 20, 'test2@baomidou.com');