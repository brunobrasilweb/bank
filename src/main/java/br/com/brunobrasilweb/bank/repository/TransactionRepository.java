package br.com.brunobrasilweb.bank.repository;

import br.com.brunobrasilweb.bank.model.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
