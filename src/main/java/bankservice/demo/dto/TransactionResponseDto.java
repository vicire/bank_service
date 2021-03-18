package bankservice.demo.dto;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class TransactionResponseDto {
    private Long id;
    private String accountNumberFrom;
    private String accountNumberTo;
    private BigDecimal amount;
    private String date;
    private String operationType;
}
