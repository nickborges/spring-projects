package br.com.hexagonal.domain;

import br.com.hexagonal.domain.adapter.DepositUseCaseAdapter;
import br.com.hexagonal.domain.adapter.WithdrawUseCaseAdapter;
import br.com.hexagonal.infrastructure.model.BankAccount;
import br.com.hexagonal.domain.port.LoadAccountPort;
import br.com.hexagonal.domain.port.SaveAccountPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class BankAccountService implements DepositUseCaseAdapter, WithdrawUseCaseAdapter {

    private final LoadAccountPort loadAccountPort;
    private final SaveAccountPort saveAccountPort;

    @Override
    public void deposit(Long id, BigDecimal amount) {
        BankAccount account = loadAccountPort.load(id)
                .orElseThrow(NoSuchElementException::new);

        account.deposit(amount);

        saveAccountPort.save(account);
    }

    @Override
    public boolean withdraw(Long id, BigDecimal amount) {
        BankAccount account = loadAccountPort.load(id)
                .orElseThrow(NoSuchElementException::new);

        boolean hasWithdrawn = account.withdraw(amount);

        if(hasWithdrawn) {
            saveAccountPort.save(account);
        }
        return hasWithdrawn;
    }
}
