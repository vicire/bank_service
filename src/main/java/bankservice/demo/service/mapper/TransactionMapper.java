package bankservice.demo.service.mapper;

import bankservice.demo.dto.TransactionResponseDto;
import bankservice.demo.entity.Transaction;
import bankservice.demo.service.AccountService;
import org.springframework.stereotype.Component;

@Component
public class TransactionMapper implements ResponseDtoMapper<TransactionResponseDto, Transaction> {
    private final AccountService accountService;

    public TransactionMapper(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public TransactionResponseDto toDto(Transaction transaction) {
        TransactionResponseDto transactionResponseDto = new TransactionResponseDto();
        transactionResponseDto.setId(transaction.getId());
        transactionResponseDto.setAccountNumberFrom(transaction
                .getAccountFrom().getAccountNumber());
        transactionResponseDto.setAccountNumberTo(transaction
                .getAccountTo().getAccountNumber());
        transactionResponseDto.setAmount(transaction.getAmount());
        transactionResponseDto.setDate(transaction.getDate().toString());
        transactionResponseDto.setOperationType(transaction.getOperationType().name());
        return transactionResponseDto;
    }
}
