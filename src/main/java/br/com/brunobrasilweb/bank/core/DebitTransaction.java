package br.com.brunobrasilweb.bank.core;

import java.math.BigDecimal;

public class DebitTransaction implements TransactionStrategy {
    @Override
    public BigDecimal apply(BigDecimal balance, BigDecimal amount) {
        return balance.subtract(amount);
    }
}

