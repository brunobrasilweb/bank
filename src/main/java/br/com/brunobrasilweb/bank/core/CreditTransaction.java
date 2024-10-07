package br.com.brunobrasilweb.bank.core;

import java.math.BigDecimal;

public class CreditTransaction implements TransactionStrategy {
    @Override
    public BigDecimal apply(BigDecimal balance, BigDecimal amount) {
        return balance.add(amount);
    }
}

