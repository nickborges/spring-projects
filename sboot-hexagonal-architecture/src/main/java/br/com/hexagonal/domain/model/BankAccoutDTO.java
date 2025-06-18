package br.com.hexagonal.domain.model;

import java.math.BigDecimal;

public class BankAccoutDTO {

    private Long id;
    private BigDecimal balance;

    public BankAccoutDTO(Long id, BigDecimal balance) {
        this.id = id;
        this.balance = balance;
    }

    public Long getId() {
        return id;
    }

    public BigDecimal getBalance() {
        return balance;
    }
}
