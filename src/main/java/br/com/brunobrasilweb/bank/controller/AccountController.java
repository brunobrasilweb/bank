package br.com.brunobrasilweb.bank.controller;

import br.com.brunobrasilweb.bank.model.dto.AccountDTO;
import br.com.brunobrasilweb.bank.model.dto.TransactionDTO;
import br.com.brunobrasilweb.bank.model.entity.Account;
import br.com.brunobrasilweb.bank.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/accounts")
public class AccountController {

    private final AccountService accountService;

    @PostMapping("/{id}/transactions")
    public ResponseEntity<AccountDTO> processTransactions(
            @PathVariable Long id,
            @RequestBody List<TransactionDTO> transactions) {
        Account account = accountService.processTransactions(id, transactions);
        return ResponseEntity.ok(new AccountDTO(account));
    }

    @GetMapping("/{id}/balance")
    public ResponseEntity<BigDecimal> getBalance(
            @PathVariable Long id) {
        return ResponseEntity.ok(accountService.getBalance(id));
    }
}

