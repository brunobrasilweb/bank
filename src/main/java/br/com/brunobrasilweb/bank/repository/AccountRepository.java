package br.com.brunobrasilweb.bank.repository;

import br.com.brunobrasilweb.bank.model.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
}
