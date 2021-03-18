package bankservice.demo.controller;

import bankservice.demo.dto.AccountRequestDto;
import bankservice.demo.dto.AccountResponseDto;
import bankservice.demo.dto.TransactionRequestDto;
import bankservice.demo.dto.TransactionResponseDto;
import bankservice.demo.entity.Account;
import bankservice.demo.service.AccountService;
import bankservice.demo.service.TransactionService;
import bankservice.demo.service.mapper.AccountMapper;
import bankservice.demo.service.mapper.TransactionMapper;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/accounts")
public class AccountController {
    private final AccountService accountService;
    private final AccountMapper accountMapper;
    private final TransactionService transactionService;
    private final TransactionMapper transactionMapper;

    public AccountController(AccountService accountService, AccountMapper accountMapper,
                             TransactionService transactionService,
                             TransactionMapper transactionMapper) {
        this.accountService = accountService;
        this.accountMapper = accountMapper;
        this.transactionService = transactionService;
        this.transactionMapper = transactionMapper;
    }

    @PostMapping
    public void create(@RequestBody AccountRequestDto accountRequestDto) {
        Account account = accountMapper.toEntity(accountRequestDto);
        account.setActive(true);
        accountService.save(account);
    }

    @GetMapping("/by-phone")
    public List<AccountResponseDto> getByPhoneNumber(@RequestParam String phoneNumber) {
        return accountService.getByPhoneNumber(phoneNumber).stream()
                .map(accountMapper::toDto)
                .collect(Collectors.toList());
    }

    @PostMapping("/transfer")
    public void transfer(@RequestBody TransactionRequestDto transactionRequestDto) {
        transactionService.transfer(accountService
                        .getByAccountNumber(transactionRequestDto.getAccountFrom()),
                accountService.getByAccountNumber(transactionRequestDto.getAccountTo()),
                transactionRequestDto.getAmount());
    }

    @GetMapping("/{accountNumber}")
    public BigDecimal getBalance(@PathVariable String accountNumber) {
        return accountService.getByAccountNumber(accountNumber).getBalance();
    }

    @GetMapping("/history/{accountNumber}")
    public List<TransactionResponseDto> getHistory(@PathVariable String accountNumber,
                                                   @RequestParam(defaultValue = "0") int page,
                                                   @RequestParam(defaultValue = "10") int size) {
        return transactionService.getAllByAccount(page, size,
                accountService.getByAccountNumber(accountNumber))
                .stream()
                .map(transactionMapper::toDto)
                .collect(Collectors.toList());
    }

    @PatchMapping("/{accountNumber}")
    public void block(@PathVariable String accountNumber) {
        accountService.getByAccountNumber(accountNumber).setActive(false);
    }
}
