create table reply(
	reply_no int primary key auto_increment,
	reply_content varchar(100) not null,
	reply_date datetime not null,
	user_id varchar(20) not null,
	board_no int not null,
	foreign key(user_id) references user(id),
	foreign key(board_no) references board(board_no)
)