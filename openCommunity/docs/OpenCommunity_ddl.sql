drop table members_tb CASCADE CONSTRAINTS PURGE;
drop table search_history_tb CASCADE CONSTRAINTS PURGE;
drop table members_info_tb CASCADE CONSTRAINTS PURGE;
drop table notice_boards_tb CASCADE CONSTRAINTS PURGE;
drop table posts_tb CASCADE CONSTRAINTS PURGE;
drop table comments_tb CASCADE CONSTRAINTS PURGE;
drop table posts_preference_tb CASCADE CONSTRAINTS PURGE;
drop table members_subscribe_tb CASCADE CONSTRAINTS PURGE;

create table members_tb (
	member_no number,
	member_email varchar2(30) not null,
	member_nickname varchar2(20) not null,
	member_pw varchar2(20) not null
);

alter table members_tb
add constraint pk_members_tb_memberno primary key (member_no);
  
create table search_history_tb (
	member_no number,
	search_word varchar2(20) not null
);
  
alter table search_history_tb
add constraint fk_search_history_tb_memberno foreign key(member_no)
references members_tb(member_no)
ON DELETE CASCADE;
 
create table members_info_tb (
	member_no number,
	join_date date default sysdate,
	last_login_date date not null,
	mileage number default 1000
);
  
alter table members_info_tb
add constraint fk_members_info_tb_memberno foreign key(member_no)
references members_tb(member_no)
ON DELETE CASCADE;
  
create table notice_boards_tb (
	board_no number,
	board_title varchar2(100) not null,
	board_tag varchar2(200) not null
);
  
alter table notice_boards_tb
add constraint pk_notice_board_tb_boardno primary key (board_no);
  
  
create table posts_tb (
	member_no number,
	board_no number,
	post_no number,
	post_title varchar(100) not null,
	post_content varchar(1000),
	create_time date default sysdate,
	post_views number default 0
);
  
alter table posts_tb
add constraint fk_posts_tb_memberno foreign key(member_no)
references members_tb(member_no)
ON DELETE CASCADE;
  
alter table posts_tb
add constraint fk_posts_tb_boardno foreign key(board_no)
references notice_boards_tb(board_no)
ON DELETE CASCADE;
  
alter table posts_tb
add constraint pk_posts_tb_boardno_postno primary key (board_no, post_no);
  
create table comments_tb (
	member_no number,
	board_no number,
	post_no number,
	comment_no number,
	content varchar(100),
	create_time date default sysdate
);
  
alter table comments_tb
add constraint fk_comments_tb_memberno foreign key(member_no)
references members_tb(member_no)
ON DELETE CASCADE;
  
alter table comments_tb
add constraint fk_comments_tb_boardno foreign key(board_no, post_no)
references posts_tb(board_no, post_no)
on delete cascade;

alter table comments_tb
add constraint pk_comments_tb_no primary key(board_no, post_no, comment_no);
  
  
create table posts_preference_tb (
	member_no number,
	board_no number,
	post_no number,
	recommend number not null
);
  
alter table posts_preference_tb
add constraint fk_posts_pref_tb_memberno foreign key(member_no)
references members_tb(member_no)
ON DELETE CASCADE;
  
alter table posts_preference_tb
add constraint fk_posts_pref_tb_boardno foreign key(board_no, post_no)
references posts_tb(board_no, post_no)
ON DELETE CASCADE;

create table members_subscribe_tb (
	member_no number,
	notice_board_no number
);

alter table members_subscribe_tb
add constraint fk_members_subs_tb_memberno foreign key(member_no)
references members_tb(member_no)
ON DELETE CASCADE;

alter table members_subscribe_tb
add constraint fk_members_subs_tb_boardno foreign key(notice_board_no)
references notice_boards_tb(board_no)
ON DELETE CASCADE;

