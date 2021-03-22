package bankservice.demo.dto;

import java.math.BigDecimal;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import lombok.Data;

@Data
public class AccountRequestDto {
    @NotEmpty(message = "Account number cannot be empty")
    private String accountNumber;
    @Positive(message = "Id must be greater than zero")
    private Long userId;
    @NotEmpty(message = "Fill in currency")
    @Size(min = 3, max = 3, message = "Currency must be defined in 3 letters")
    private String currency;
    @DecimalMin(value = "0")
    private BigDecimal balance;
}
