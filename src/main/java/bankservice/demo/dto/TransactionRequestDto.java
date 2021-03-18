package bankservice.demo.dto;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class TransactionRequestDto {
    private String accountFrom;
    private String accountTo;
    private BigDecimal amount;
}
