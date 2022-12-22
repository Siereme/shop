create table user_auth
(
   id integer not null auto_increment,
   username varchar(255) not null,
   password varchar(255) not null,
   primary key(id)
);