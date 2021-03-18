package bankservice.demo.dto;

import lombok.Data;

@Data
public class UserResponseDto {
    private Long id;
    private String name;
    private String phoneNumber;
    private String dateOfBirth;
}
