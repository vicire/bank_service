package bankservice.demo.entity;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "accounts")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "account_number", unique = true, nullable = false)
    private String accountNumber;
    @ManyToOne
    private User user;
    @Enumerated(EnumType.STRING)
    private Currency currency;
    private BigDecimal balance;
    private boolean status;

    @Override
    public String toString() {
        return "{Account - "
                + "id " + id
                + ", account number: " + accountNumber
                + ", currency: " + currency
                + ", balance " + balance
                + ", status " + status + "}";
    }
}
