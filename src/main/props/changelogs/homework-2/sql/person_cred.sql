CREATE TABLE person_cred
(
 "id"        bigserial NOT NULL,
 login     varchar(50) NOT NULL,
 person_id bigint NOT NULL,
 password  varchar(50) NOT NULL,
 PRIMARY KEY ( "id" ),
 FOREIGN KEY ( person_id ) REFERENCES person ( "id" )
);
