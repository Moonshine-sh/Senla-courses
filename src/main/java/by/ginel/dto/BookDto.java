package by.ginel.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Setter
@Getter
@SuperBuilder
public class BookDto extends AbstractDto{
    private String name;
    private String description;
    private String picPath;
}
