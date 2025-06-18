package br.com.hexagonal.infrastructure.repository;

import br.com.hexagonal.infrastructure.model.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringDataBankAccountRepository extends JpaRepository<BankAccount, Long> {
}
