use testdb;

create table if not exists testdb.testtable (
	id INT,
	name varchar(50),
	country varchar(50)
) engine=InnoDB;

show tables;