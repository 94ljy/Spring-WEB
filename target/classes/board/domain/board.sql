create table board(
	board_no int primary key auto_Increment,
	board_title varchar(30) not null,
	board_content varchar(500) not null,
	board_time datetime not null,
	board_count int not null,
	user_id varchar(20) not null,
	foreign key(user_id) references user(id)
);