create table t_book
(
	BOOK_ID VARCHAR(40) default '' not null
		primary key,
	BOOK_NAME VARCHAR(100) default '' not null,
	BOOK_PRICE VARCHAR(20) default '' not null,
	BOOK_NUM VARCHAR(10) default '' not null,
	BOOK_ACT VARCHAR(2) default '00' not null,
	BOOK_IMG BLOB(65535) null,
	BOOK_DESC VARCHAR(300) default '暂无' null,
	BOOK_MORE_DESC VARCHAR(1000) default '暂无' null,
	BOOK_SPE_PRICE VARCHAR(20) null
)
;

comment on column t_book.BOOK_ACT is '00-无活动，01-新品，02-促销，03-热卖'
;

create table t_cart
(
	CART_ID VARCHAR(40) default '' not null
		primary key,
	USER_ID VARCHAR(40) default '' not null,
	BOOK_ID VARCHAR(40) null,
	BOOK_NUM VARCHAR(10) default '' not null
)
;

create index t_cart_t_book_BOOK_ID_fk
	on t_cart (BOOK_ID)
;

create table t_notice
(
	notice_id VARCHAR(40) default '' not null
		primary key,
	notice_title VARCHAR(255) default '' not null,
	notice_content VARCHAR(500) default '' not null,
	notice_time VARCHAR(40) default '' not null,
	user_id VARCHAR(40) default '' not null
)
;

comment on column t_notice.user_id is '创建者'
;

create table t_trade_info
(
	trade_id VARCHAR(40) default '' not null
		primary key,
	user_id VARCHAR(40) default '' not null,
	trade_time VARCHAR(50) default '' not null,
	trade_money VARCHAR(10) default '' not null,
	trade_type VARCHAR(2) default '' not null
)
;

comment on column t_trade_info.trade_type is '01-充值 02-扣减'
;

create table t_user
(
	USER_ID VARCHAR(40) default '' not null
		primary key,
	USER_NAME VARCHAR(10) default '' not null,
	USER_TYPE VARCHAR(2) default '01' null,
	USER_PASSWORD VARCHAR(20) default '' not null,
	USER_MOBILE VARCHAR(11) null,
	USER_ADDR VARCHAR(40) null,
	USER_EMAIL VARCHAR(40) null,
	USER_BALANCE VARCHAR(10) default '0' null
)
;

create unique index t_user_USER_NAME_uindex
	on t_user (USER_NAME)
;

alter table t_cart
	add constraint t_cart_t_user_USER_ID_fk
		foreign key (USER_ID) references t_user
;

