package bankservice.demo.service;

import bankservice.demo.entity.Account;
import bankservice.demo.entity.Transaction;
import java.math.BigDecimal;
import java.util.List;

public interface TransactionService {
    List<Transaction> getAllByAccount(int page, int size, Account account);

    void transfer(Account fromAccount, Account toAccount, BigDecimal amount);
}
