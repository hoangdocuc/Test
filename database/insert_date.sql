use divisima;

insert into role(code,name) values('ADMIN','Quản trị');
insert into role(code,name) values('USER','Người dùng');

insert into users(username,password,fullname,email,phone,status)
values('admin','$2a$10$/RUbuT9KIqk6f8enaTQiLOXzhnUkiwEJRdtzdrMXXwU7dgnLKTCYG','hoàng đỗ cúc','cuc@gmail.com','123456789',1);
insert into users(username,password,fullname,email,phone,status)
values('nguyenvana','$2a$10$/RUbuT9KIqk6f8enaTQiLOXzhnUkiwEJRdtzdrMXXwU7dgnLKTCYG','nguyễn văn a','a@gmail.com','123456789',1);
insert into users(username,password,fullname,email,phone,status)
values('nguyenvanb','$2a$10$/RUbuT9KIqk6f8enaTQiLOXzhnUkiwEJRdtzdrMXXwU7dgnLKTCYG','nguyễn văn b','b@gmail.com','123456789',1);

insert into user_role(user_id,role_id) values (1,1);
insert into user_role(user_id,role_id) values (2,2);
insert into user_role(user_id,role_id) values (3,2);

