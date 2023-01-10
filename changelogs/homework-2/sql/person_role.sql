CREATE TABLE person_role
(
 "id"        serial NOT NULL,
 person_id int NOT NULL,
 role_id   int NOT NULL,
 PRIMARY KEY ( "id" ),
 FOREIGN KEY ( person_id ) REFERENCES person ( "id" ),
 FOREIGN KEY ( role_id ) REFERENCES role ( "id" )
);

CREATE INDEX FK_2 ON person_role
(
 person_id
);

CREATE INDEX FK_3 ON person_role
(
 role_id
);