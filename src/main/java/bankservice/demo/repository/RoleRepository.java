package bankservice.demo.repository;

import bankservice.demo.entity.Role;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RoleRepository extends JpaRepository<Role, Long> {
    @Query("FROM Role WHERE roleName =: rolename")
    Optional<Role> getByName(Role.RoleName roleName);
}
