package bankservice.demo.service;

import bankservice.demo.entity.Role;

public interface RoleService {
    Role save(Role role);

    Role getByName(String name);
}
