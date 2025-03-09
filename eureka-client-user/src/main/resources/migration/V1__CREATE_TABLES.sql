create table hibernate_sequence (
    next_val bigint
) engine = MyISAM;

insert into hibernate_sequence values ( 1 );
insert into hibernate_sequence values ( 1 );

CREATE TABLE user (
    id BIGINT NOT NULL AUTO_INCREMENT,
    password VARCHAR(64) NOT NULL ,
    username VARCHAR(64) NOT NULL UNIQUE ,
    PRIMARY KEY (id)
) engine = MyISAM;

CREATE TABLE todo (
    id BIGINT NOT NULL AUTO_INCREMENT,
    title VARCHAR(64) NOT NULL ,
    completed BOOLEAN NOT NULL ,
    PRIMARY KEY (id)
) engine = MyISAM;

#drop table if exists to#do;
#drop table if exists user;

#create table todo (
#    id bigint not null auto_increment,
#    completed bit,
#    title varchar(255),
#    user_id bigint,
#    primary key (id)
#) engine=InnoDB;

#create table user (
#    id bigint not null auto_increment,
#    password varchar(255),
#    username varchar(255),
#    primary key (id)
#) engine=InnoDB;

alter table todo add constraint todo_foreign_key foreign key (user_id) references user (id)