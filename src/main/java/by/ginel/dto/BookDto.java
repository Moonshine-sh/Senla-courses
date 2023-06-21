package by.ginel.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Setter
@Getter
@SuperBuilder
public class BookDto extends AbstractDto{
    private String name;
    private String description;
    private String picPath;
    private List<String> authors;
    private List<String> genres;

}
