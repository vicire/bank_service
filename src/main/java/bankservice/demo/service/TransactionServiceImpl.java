package bankservice.demo.service;

import bankservice.demo.entity.Account;
import bankservice.demo.entity.Transaction;
import bankservice.demo.repository.AccountRepository;
import bankservice.demo.repository.TransactionRepository;
import bankservice.demo.service.util.ClientService;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository transactionRepository;
    private final AccountRepository accountService;
    private final ClientService clientService;

    @Override
    public List<Transaction> getAllByAccount(int page, int size, Account account) {
        PageRequest pageRequest = PageRequest.of(page, size,
                Sort.by("date").descending().and(Sort.by("id")));
        return transactionRepository.getAllByAccount(account, pageRequest);
    }

    @Override
    public void transfer(Account fromAccount, Account toAccount, BigDecimal amount) {
        Transaction transactionOut = new Transaction();
        transactionOut.setAccountTo(toAccount);
        transactionOut.setAccountFrom(fromAccount);
        transactionOut.setAmount(amount);
        transactionOut.setDate(LocalDateTime.now());
        transactionOut.setOperationType(Transaction.OperationType.OUTCOMING);
        transactionRepository.save(transactionOut);
        fromAccount.setBalance(fromAccount.getBalance().subtract(toAccount.getBalance()));
        if (fromAccount.getBalance().compareTo(BigDecimal.ZERO) < 0) {
            throw new RuntimeException("There are not enough money on account " + fromAccount);
        }
        accountService.save(fromAccount);

        Transaction transactionIn = new Transaction();
        transactionIn.setAccountFrom(fromAccount);
        transactionIn.setAccountTo(toAccount);
        transactionIn.setDate(LocalDateTime.now());
        transactionIn.setOperationType(Transaction.OperationType.INCOMING);
        if (fromAccount.getCurrency() != toAccount.getCurrency()) {
            BigDecimal rate = clientService.getRate(LocalDate.now(),
                    fromAccount.getCurrency(), toAccount.getCurrency());
            amount = amount.multiply(rate);
        }
        transactionIn.setAmount(amount);
        toAccount.setBalance(toAccount.getBalance().add(amount));
        transactionRepository.save(transactionIn);
        accountService.save(toAccount);
    }
}
