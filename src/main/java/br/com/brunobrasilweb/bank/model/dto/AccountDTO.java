package br.com.brunobrasilweb.bank.model.dto;

import br.com.brunobrasilweb.bank.core.CreditTransaction;
import br.com.brunobrasilweb.bank.core.TransactionType;
import br.com.brunobrasilweb.bank.model.entity.Account;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountDTO {

    private Long id;
    private BigDecimal balance;
    private List<TransactionDTO> transactions;

    public AccountDTO(Account account) {
        this.id = account.getId();
        this.balance = account.getBalance();
        this.transactions = account.getTransactions().stream()
                .map(t -> new TransactionDTO(t.getAmount(),
                        (t.getStrategy() instanceof CreditTransaction) ? TransactionType.CREDIT : TransactionType.DEBIT))
                .collect(Collectors.toList());
    }

}
