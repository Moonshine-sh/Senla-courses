package by.ginel.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Setter
@Getter
@SuperBuilder
public class AuthorDto extends AbstractDto{
    private String name;
}
