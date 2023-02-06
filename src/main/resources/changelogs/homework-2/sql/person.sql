CREATE TABLE person
(
 "id"         bigserial NOT NULL,
 first_name varchar(50) NOT NULL,
 last_name  varchar(50) NOT NULL,
 email      varchar(50) NOT NULL,
 mob_num    varchar(50) NOT NULL,
 locked     boolean NOT NULL,
 enabled    boolean NOT NULL,
 PRIMARY KEY ( "id" )
);