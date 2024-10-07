package br.com.brunobrasilweb.bank;

import br.com.brunobrasilweb.bank.core.TransactionType;
import br.com.brunobrasilweb.bank.model.dto.TransactionDTO;
import br.com.brunobrasilweb.bank.model.entity.Account;
import br.com.brunobrasilweb.bank.repository.AccountRepository;
import br.com.brunobrasilweb.bank.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
public class AccountServiceTest {

    @Autowired
    private AccountService accountService;

    @Autowired
    private AccountRepository accountRepository;

    @Test
    public void testProcessTransaction() {
        Account account = new Account();
        accountRepository.save(account);

        List<TransactionDTO> transactions = List.of(
                new TransactionDTO(BigDecimal.valueOf(200.51), TransactionType.CREDIT),
                new TransactionDTO(BigDecimal.valueOf(100.92), TransactionType.DEBIT)
        );
        accountService.processTransactions(account.getId(), transactions);

        BigDecimal balance = accountService.getBalance(account.getId());
        assertEquals(BigDecimal.valueOf(99.59), balance);
    }
}

