-- 常规测试
CREATE TABLE users (
   ID int(20) NOT NULL ,
   CODE  varchar(16) DEFAULT NULL,
   PRIMARY KEY ( ID )
) ;

-- orm 测试的

CREATE TABLE customers (
   ID int(20) NOT NULL ,
   CODE  varchar(16) DEFAULT NULL,
   NAME  varchar(16) DEFAULT NULL,
   PRIMARY KEY ( ID )
) ;

insert into  customers values (1,'a','用户一');
insert into  customers values (2,'b','用户二');
insert into  customers values (3,'c','用户三');


CREATE TABLE orders (
     ID int(20) NOT NULL ,
     NAME  varchar(16) DEFAULT NULL,
     cust_id int(20) ,
   PRIMARY KEY ( ID )
) ;

insert into  orders values (1,'a',1);
insert into  orders values (2,'b',1);
insert into  orders values (3,'c',2);
insert into  orders values (4,'d',2);
