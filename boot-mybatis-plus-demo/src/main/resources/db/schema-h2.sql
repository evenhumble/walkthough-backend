DROP TABLE IF EXISTS user;

CREATE TABLE user
(
	id BIGINT(20) NOT NULL COMMENT '主键ID',
	name VARCHAR(30) NULL DEFAULT NULL COMMENT '姓名',
	age INT(11) NULL DEFAULT NULL COMMENT '年龄',
	email VARCHAR(50) NULL DEFAULT NULL COMMENT '邮箱',
	PRIMARY KEY (id)
);

create Table project (
    id BIGINT(20) NOT NULL COMMENT '主键ID',
    project_name VARCHAR(30) NULL DEFAULT NULL COMMENT 'project name',
    project_summary VARCHAR(300) NULL DEFAULT NULL COMMENT 'project summary',
    parent_id BIGINT(20) COMMENT 'parent project/module ID',
    CREATE_TIME timestamp COMMENT 'creation time',
    PRIMARY KEY (id)
)