CREATE TABLE person_cred
(
 "id"        serial NOT NULL,
 login     varchar(50) NOT NULL,
 person_id int NOT NULL,
 password  varchar(50) NOT NULL,
 PRIMARY KEY ( "id" ),
 FOREIGN KEY ( person_id ) REFERENCES person ( "id" )
);

CREATE INDEX FK_1 ON person_cred
(
 person_id
);