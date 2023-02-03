package by.ginel.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Setter
@Getter
@SuperBuilder
public class PersonDto extends AbstractDto{
    private String firstName;
    private String lastName;
    private String email;
    private String mobNum;
    private Boolean locked;
    private Boolean enabled;

    private Long personCredentialsId;
    private List<Long> roles;
    //private List<Long> orderIds;
}
