package by.ginel.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.sql.Timestamp;

@Setter
@Getter
@SuperBuilder
public class VerificationTokenDto extends AbstractDto{
    private String token;
    private Long personId;
    private Timestamp expiryDate;
}
