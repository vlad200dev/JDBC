#select * from users;
#delete from users where id>1
#select * from users;
#select name,age from users where id=1;
#update users set name='Steve', age=55 where id=1;
#delete from users;
#delete from users where id=7;
# insert into users(name, age, email) values('Jack',29, 'jack@mail.ru');
# select * from users;

# create table animal(
#     id int NOT NULL PRIMARY KEY AUTO_INCREMENT,
#     animal_name varchar(45),
#     animal_description varchar(45)
#      );

# create table user(
#     id int not null primary key auto_increment,
#     username varchar(40) not null,
#     password varchar(40) not null
# );

# insert into user(username, password) values('test','test');

# select * from user;

# insert into user (username, password)
# values ('test2','test2');


create table book(
    id int not null primary key auto_increment,
    title varchar(45) not null,
    description varchar(45) not null,
    rating double,
    published date,
    created date,
    icode longblob
)
