package by.ginel.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RegisterDto {
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private String mobNum;
}
