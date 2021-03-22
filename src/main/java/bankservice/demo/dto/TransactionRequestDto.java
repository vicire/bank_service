package bankservice.demo.dto;

import java.math.BigDecimal;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import lombok.Data;

@Data
public class TransactionRequestDto {
    @NotEmpty(message = "Fill in account number you send ")
    private String accountFrom;
    @NotEmpty(message = "Fill in account number")
    private String accountTo;
    @NotEmpty(message = "Amount cannot be empty")
    @Positive
    private BigDecimal amount;
}
