CREATE TABLE verification_token
(
 "id"          bigserial NOT NULL,
 "token"       varchar(50) NOT NULL,
 person_id   bigint NOT NULL,
 expiry_date timestamp NOT NULL,
 PRIMARY KEY ( "id" ),
 FOREIGN KEY ( person_id ) REFERENCES person ( "id" )
);
