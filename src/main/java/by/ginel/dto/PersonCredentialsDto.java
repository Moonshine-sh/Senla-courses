package by.ginel.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Setter
@Getter
@SuperBuilder
public class PersonCredentialsDto extends AbstractDto{
    private String login;
    private String password;
    private Long personId;
}
