package bankservice.demo.service.mapper;

import bankservice.demo.dto.UserRequestDto;
import bankservice.demo.dto.UserResponseDto;
import bankservice.demo.entity.User;
import java.time.LocalDate;
import org.springframework.stereotype.Component;

@Component
public class UserMapper implements RequestDtoMapper<User, UserRequestDto>,
        ResponseDtoMapper<UserResponseDto, User> {
    @Override
    public User toEntity(UserRequestDto userRequestDto) {
        User user = new User();
        user.setName(userRequestDto.getName());
        user.setPassword(userRequestDto.getPassword());
        user.setPhoneNumber(userRequestDto.getPhoneNumber());
        user.setDateOfBirth(LocalDate.parse(userRequestDto.getDateOfBirth()));
        return user;
    }

    @Override
    public UserResponseDto toDto(User user) {
        UserResponseDto responseDto = new UserResponseDto();
        responseDto.setId(user.getId());
        responseDto.setName(user.getName());
        responseDto.setPhoneNumber(user.getPhoneNumber());
        responseDto.setDateOfBirth(user.getDateOfBirth().toString());
        return responseDto;
    }
}
