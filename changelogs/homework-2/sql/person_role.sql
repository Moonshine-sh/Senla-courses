CREATE TABLE person_role
(
 person_id bigint NOT NULL,
 role_id   bigint NOT NULL,
 PRIMARY KEY ( person_id,role_id ),
 FOREIGN KEY ( person_id ) REFERENCES person ( "id" ),
 FOREIGN KEY ( role_id ) REFERENCES role ( "id" )
);
