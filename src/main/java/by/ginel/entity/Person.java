package by.ginel.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class Person extends AbstractEntity{
    private String firstName;
    private String lastName;
    private String email;
    private String mobNum;
    private Boolean locked;
    private Boolean enabled;
}