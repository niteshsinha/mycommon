GRANT USAGE ON *.* TO 'testuser';
DROP USER 'testuser';
FLUSH PRIVILEGES;

select user from mysql.user;

create user 'testuser' identified by 'testuser';
select user from mysql.user;
