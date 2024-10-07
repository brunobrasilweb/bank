package br.com.brunobrasilweb.bank.model.entity;

import br.com.brunobrasilweb.bank.core.TransactionStrategy;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    private BigDecimal amount;
    private LocalDateTime timestamp;

    @Transient
    private TransactionStrategy strategy;

    public Transaction(TransactionStrategy strategy) {
        this.strategy = strategy;
        this.timestamp = LocalDateTime.now();
    }

    public BigDecimal applyTransaction(BigDecimal balance) {
        return strategy.apply(balance, this.amount);
    }

}

