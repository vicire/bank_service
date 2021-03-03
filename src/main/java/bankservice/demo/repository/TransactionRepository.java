package bankservice.demo.repository;

import bankservice.demo.entity.Account;
import bankservice.demo.entity.Transaction;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    @Query("FROM Transaction t WHERE t.accountTo = ?1 OR t.accountFrom = ?1")
    List<Transaction> getAllByAccount(Account account, Pageable pageRequest);
}
