package br.com.brunobrasilweb.bank.model.dto;

import br.com.brunobrasilweb.bank.core.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDTO {

    private BigDecimal amount;
    private TransactionType type;

}
