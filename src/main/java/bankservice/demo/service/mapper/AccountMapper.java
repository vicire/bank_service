package bankservice.demo.service.mapper;

import bankservice.demo.dto.AccountRequestDto;
import bankservice.demo.dto.AccountResponseDto;
import bankservice.demo.entity.Account;
import bankservice.demo.service.UserService;
import org.springframework.stereotype.Component;

@Component
public class AccountMapper implements RequestDtoMapper<Account, AccountRequestDto>,
        ResponseDtoMapper<AccountResponseDto, Account> {
    private final UserService userService;

    public AccountMapper(UserService userService) {
        this.userService = userService;
    }

    @Override
    public Account toEntity(AccountRequestDto accountRequestDto) {
        Account account = new Account();
        account.setAccountNumber(accountRequestDto.getAccountNumber());
        account.setUser(userService.getById(accountRequestDto.getUserId()));
        account.setCurrency(accountRequestDto.getCurrency());
        account.setBalance(accountRequestDto.getBalance());
        return account;
    }

    @Override
    public AccountResponseDto toDto(Account account) {
        AccountResponseDto accountResponseDto = new AccountResponseDto();
        accountResponseDto.setId(account.getId());
        accountResponseDto.setAccountNumber(account.getAccountNumber());
        accountResponseDto.setUserId(account.getUser().getId());
        accountResponseDto.setCurrency(account.getCurrency());
        accountResponseDto.setBalance(account.getBalance());
        return accountResponseDto;
    }
}
