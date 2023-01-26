package by.ginel.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.sql.Timestamp;

@Setter
@Getter
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class VerificationTokenDto extends AbstractDto{
    private String token;
//    private Long personId;
    private Timestamp expiryDate;
}
