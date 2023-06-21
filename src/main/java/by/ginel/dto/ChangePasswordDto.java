package by.ginel.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Setter
@Getter
@NoArgsConstructor
public class ChangePasswordDto {
    private String oldPassword;
    private String newPassword;
    private String confirmPassword;
}
