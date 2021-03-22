package bankservice.demo.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import lombok.Data;

@Data
public class UserRequestDto {
    @NotEmpty(message = "You have to fill in your name")
    private String name;
    @NotEmpty(message = "You need to fill in your phone number")
    private String phoneNumber;
    @NotEmpty(message = "Password cannot be empty")
    private String password;
    @NotEmpty(message = "You need to fill in your birth date")
    @Pattern(regexp = "[\\d]{4}-[\\d]{2}-[\\d]{2}",
            message = "Your date must be like yyyy-mm-dd")
    private String dateOfBirth;
}
