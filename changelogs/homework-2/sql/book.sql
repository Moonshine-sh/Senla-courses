CREATE TABLE book
(
 "id"          bigserial NOT NULL,
 "name"        varchar(50) NOT NULL,
 description varchar(50) NOT NULL,
 pic_path    varchar(50) NOT NULL,
 PRIMARY KEY ( "id" )
);