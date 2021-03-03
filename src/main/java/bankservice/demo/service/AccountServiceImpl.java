package bankservice.demo.service;

import bankservice.demo.entity.Account;
import bankservice.demo.exception.NoSuchEntityException;
import bankservice.demo.repository.AccountRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public void create(Account account) {
        accountRepository.save(account);
    }

    @Override
    public List<Account> getByPhoneNumber(String phoneNumber) {
        return accountRepository.getByPhoneNumber(phoneNumber);
    }

    @Override
    public Account getByAccountNumber(String accountNumber) {
        return accountRepository.findAccountByAccountNumber(accountNumber).orElseThrow(()
                -> new NoSuchEntityException("There is no account with number " + accountNumber));
    }

    @Override
    public void block(String accountNumber) {
        Account account = getByAccountNumber(accountNumber);
        account.setStatus(false);
        accountRepository.save(account);
    }
}
