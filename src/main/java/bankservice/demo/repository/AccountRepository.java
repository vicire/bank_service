package bankservice.demo.repository;

import bankservice.demo.entity.Account;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AccountRepository extends JpaRepository<Account, Long> {
    @Query("FROM Account a WHERE a.user.phoneNumber = ?1")
    List<Account> getByPhoneNumber(String phoneNumber);

    Optional<Account> findAccountByAccountNumber(String accountNumber);
}
