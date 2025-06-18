package br.com.hexagonal.infrastructure.component;

import br.com.hexagonal.infrastructure.model.BankAccount;
import br.com.hexagonal.domain.port.LoadAccountPort;
import br.com.hexagonal.domain.port.SaveAccountPort;
import br.com.hexagonal.infrastructure.repository.SpringDataBankAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class BankAccountComponent implements LoadAccountPort, SaveAccountPort {

    @Autowired
    private SpringDataBankAccountRepository repository;

    @Override
    public Optional<BankAccount> load(Long id) {
        return repository.findById(id);
    }

    @Override
    public void save(BankAccount bankAccount) {
        repository.save(bankAccount);
    }
}
