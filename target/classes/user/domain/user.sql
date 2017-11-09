create table user(
	id varchar(20) primary key,
	password varchar(20) not null
);


create table userInfo(
	id varchar(20) not null unique,
	name varchar(15) not null,
	email varchar(30) not null,
	subName varchar(20) not null unique,
	phoneNumber varchar(20) not null,
	foreign key(id) references user(id)
);