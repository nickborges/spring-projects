package br.com.hexagonal.domain.adapter;

import java.math.BigDecimal;

public interface DepositUseCaseAdapter {

    void deposit(Long id, BigDecimal amount);

}
