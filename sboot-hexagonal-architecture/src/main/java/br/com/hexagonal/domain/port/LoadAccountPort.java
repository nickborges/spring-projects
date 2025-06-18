package br.com.hexagonal.domain.port;

import br.com.hexagonal.infrastructure.model.BankAccount;

import java.util.Optional;

public interface LoadAccountPort {

    Optional<BankAccount> load(Long id);

}
