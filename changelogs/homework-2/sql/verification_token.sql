CREATE TABLE verification_token
(
 "id"          serial NOT NULL,
 "token"       varchar(50) NOT NULL,
 person_id   int NOT NULL,
 expiry_date date NOT NULL,
 PRIMARY KEY ( "id" ),
 FOREIGN KEY ( person_id ) REFERENCES person ( "id" )
);

CREATE INDEX FK_4 ON verification_token
(
 person_id
);