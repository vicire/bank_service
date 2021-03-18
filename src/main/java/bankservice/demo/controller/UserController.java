package bankservice.demo.controller;

import bankservice.demo.dto.UserRequestDto;
import bankservice.demo.dto.UserResponseDto;
import bankservice.demo.entity.Role;
import bankservice.demo.entity.User;
import bankservice.demo.service.RoleService;
import bankservice.demo.service.UserService;
import bankservice.demo.service.mapper.UserMapper;
import java.util.Set;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;
    private final RoleService roleService;

    @PostMapping
    public void create(@RequestBody UserRequestDto userRequestDto) {
        User user = userMapper.toEntity(userRequestDto);
        Role role = roleService.getByName("USER");
        user.setRoles(Set.of(role));
        userService.create(user);
    }

    @PutMapping("/{userId}")
    public void update(@PathVariable Long userId,
                       @RequestBody UserRequestDto userRequestDto) {
        User user = userMapper.toEntity(userRequestDto);
        user.setId(userId);
        user.setRoles(userService.getById(userId).getRoles());
        userService.update(user);
    }

    @GetMapping("/{userId}")
    public UserResponseDto getById(@PathVariable Long userId) {
        return userMapper.toDto(userService.getById(userId));
    }

    @GetMapping("/by-phone")
    public UserResponseDto getByPhoneNumber(@RequestParam String phoneNumber) {
        return userMapper.toDto(userService.getByPhoneNumber(phoneNumber));
    }

    @DeleteMapping("/{userId}")
    public void remove(@PathVariable Long userId) {
        userService.delete(userId);
    }
}
