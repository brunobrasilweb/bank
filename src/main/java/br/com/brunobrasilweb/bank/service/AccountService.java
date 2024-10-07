package br.com.brunobrasilweb.bank.service;

import br.com.brunobrasilweb.bank.core.CreditTransaction;
import br.com.brunobrasilweb.bank.core.DebitTransaction;
import br.com.brunobrasilweb.bank.core.TransactionStrategy;
import br.com.brunobrasilweb.bank.core.TransactionType;
import br.com.brunobrasilweb.bank.model.dto.TransactionDTO;
import br.com.brunobrasilweb.bank.model.entity.Account;
import br.com.brunobrasilweb.bank.model.entity.Transaction;
import br.com.brunobrasilweb.bank.repository.AccountRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;

    @Transactional
    public Account processTransactions(Long accountId, List<TransactionDTO> transactionDtos) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new ResourceNotFoundException("Account not found"));

        synchronized (account) {
            transactionDtos.forEach(transactionDto -> {
                TransactionStrategy strategy = new DebitTransaction();

                if (transactionDto.getType() == TransactionType.CREDIT) {
                    strategy = new CreditTransaction();
                }

                Transaction transaction = Transaction.builder()
                        .account(account)
                        .amount(transactionDto.getAmount())
                        .timestamp(LocalDateTime.now())
                        .strategy(strategy)
                        .build();

                account.setBalance(transaction.applyTransaction(account.getBalance()));
                account.getTransactions().add(transaction);
            });
        }

        return accountRepository.save(account);
    }

    public BigDecimal getBalance(Long accountId) {
        return accountRepository.findById(accountId)
                .orElseThrow(() -> new ResourceNotFoundException("Account not found"))
                .getBalance();
    }
}

