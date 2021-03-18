package bankservice.demo.service;

import bankservice.demo.entity.Role;
import bankservice.demo.entity.User;
import java.time.LocalDate;
import java.time.Month;
import java.util.Set;
import javax.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class InjectData {
    private final RoleService roleService;
    private final UserService userService;

    @PostConstruct
    private void injectRoles() {
        Role admin = new Role();
        admin.setRoleName(Role.RoleName.ADMIN);
        roleService.save(admin);
        Role user = new Role();
        user.setRoleName(Role.RoleName.USER);
        roleService.save(user);
        User firstAdmin = new User();
        firstAdmin.setName("Gocik Makarov");
        firstAdmin.setDateOfBirth(LocalDate.of(1993, Month.valueOf("MARCH"), 22));
        firstAdmin.setPhoneNumber("35902");
        firstAdmin.setPassword("12345");
        firstAdmin.setRoles(Set.of(admin));
        userService.create(firstAdmin);
    }
}
