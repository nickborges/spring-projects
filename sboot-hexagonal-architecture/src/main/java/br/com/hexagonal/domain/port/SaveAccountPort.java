package br.com.hexagonal.domain.port;

import br.com.hexagonal.infrastructure.model.BankAccount;

public interface SaveAccountPort {

    void save(BankAccount bankAccount);

}
