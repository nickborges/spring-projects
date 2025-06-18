package br.com.hexagonal.domain.adapter;

import java.math.BigDecimal;

public interface WithdrawUseCaseAdapter {

    boolean withdraw(Long id, BigDecimal amount);

}
