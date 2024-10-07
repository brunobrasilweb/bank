package br.com.brunobrasilweb.bank.core;

import java.math.BigDecimal;

public interface TransactionStrategy {
    BigDecimal apply(BigDecimal balance, BigDecimal amount);
}
