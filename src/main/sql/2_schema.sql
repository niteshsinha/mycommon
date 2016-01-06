create database if not exists testdb DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
alter database testdb DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
use testdb;
grant all on testdb to 'testuser' ;
grant all on testdb to 'testuser'@'%' ;
grant all on testdb to 'testuser'@'localhost' ;

grant all on testdb.* to 'testuser' identified by 'testuser';
grant all on testdb.* to 'testuser'@'%' identified by 'testuser';
grant all on testdb.* to 'testuser'@'localhost' identified by 'testuser';

show databases;
