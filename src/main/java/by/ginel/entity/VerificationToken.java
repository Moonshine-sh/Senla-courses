package by.ginel.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.sql.Timestamp;

@Getter
@Setter
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class VerificationToken extends AbstractEntity {
    private String token;
//    private Person person;
    private Timestamp expiryDate;
}
