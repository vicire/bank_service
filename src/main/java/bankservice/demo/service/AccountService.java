package bankservice.demo.service;

import bankservice.demo.entity.Account;
import java.util.List;

public interface AccountService {
    void save(Account account);

    List<Account> getByPhoneNumber(String phoneNumber);

    Account getByAccountNumber(String accountNumber);
}
