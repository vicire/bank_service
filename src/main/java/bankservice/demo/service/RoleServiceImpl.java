package bankservice.demo.service;

import bankservice.demo.entity.Role;
import bankservice.demo.exception.NoSuchEntityException;
import bankservice.demo.repository.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    @Override
    public Role save(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public Role getByName(String name) {
        return roleRepository.getByRoleName(Role.RoleName.valueOf(name)).orElseThrow(()
                -> new NoSuchEntityException("There is no such role " + name));
    }
}
