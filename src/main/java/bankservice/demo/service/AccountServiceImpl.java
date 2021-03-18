package bankservice.demo.service;

import bankservice.demo.entity.Account;
import bankservice.demo.exception.NoSuchEntityException;
import bankservice.demo.repository.AccountRepository;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;

    @Override
    public void save(Account account) {
        accountRepository.save(account);
    }

    @Override
    public List<Account> getByPhoneNumber(String phoneNumber) {
        return accountRepository.getByUserPhoneNumber(phoneNumber);
    }

    @Override
    public Account getByAccountNumber(String accountNumber) {
        return accountRepository.findAccountByAccountNumber(accountNumber).orElseThrow(()
                -> new NoSuchEntityException("There is no account with number " + accountNumber));
    }
}
