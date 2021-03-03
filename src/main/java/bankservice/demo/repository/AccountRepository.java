package bankservice.demo.repository;

import bankservice.demo.entity.Account;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
    List<Account> getByUserPhoneNumber(String phoneNumber);

    Optional<Account> findAccountByAccountNumber(String accountNumber);
}
