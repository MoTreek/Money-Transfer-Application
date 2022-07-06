package com.techelevator.tenmo.model;

import java.math.BigDecimal;

public class Account {
    private Long id;
    private BigDecimal balance;
    private int user_id;

    public Account(){};

    public Account(Long id, int user_id, BigDecimal balance) {
        this.id = id;
        this.balance = balance;
        this.user_id = user_id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

}
