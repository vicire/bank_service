package bankservice.demo.dto;

import bankservice.demo.entity.Currency;
import java.math.BigDecimal;
import lombok.Data;

@Data
public class AccountRequestDto {
    private String accountNumber;
    private Long userId;
    private Currency currency;
    private BigDecimal balance;
}
