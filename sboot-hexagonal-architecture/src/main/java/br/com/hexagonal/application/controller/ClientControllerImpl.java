package br.com.hexagonal.application.controller;

import br.com.hexagonal.domain.adapter.DepositUseCaseAdapter;
import br.com.hexagonal.domain.adapter.WithdrawUseCaseAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class ClientControllerImpl {

    @Autowired
    private DepositUseCaseAdapter depositUseCaseAdapter;

    @Autowired
    private WithdrawUseCaseAdapter withdrawUseCaseAdapter;

    //@PostMapping(value = "/{id}/deposit/{amount}")
    @GetMapping
    void deposit() {
        depositUseCaseAdapter.deposit(1L, new BigDecimal(10));
    }

    @PostMapping(value = "/{id}/withdraw/{amount}")
    void withdraw(@PathVariable final Long id, @PathVariable final BigDecimal amount) {
        withdrawUseCaseAdapter.withdraw(id, amount);
    }

}
