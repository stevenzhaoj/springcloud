drop table user if exists;
CREATE TABLE user (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(40) ,
  `name` varchar(20) ,
  `age` int(3) ,
  `balance` decimal(10,2) ,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=65 DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=COMPACT;

insert into user(username, name, age, balance) values('account1', '张三', 20, 100.00);
insert into user(username, name, age, balance) values('account2', '李四', 28, 180.00);
insert into user(username, name, age, balance) values('account3', '王五', 32, 280.00);
