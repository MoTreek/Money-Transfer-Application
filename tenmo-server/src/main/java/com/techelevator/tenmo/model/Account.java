package com.techelevator.tenmo.model;

import java.math.BigDecimal;

public class Account {
    private Long accountId;
    private BigDecimal balance;
    private int user_id;

    public Account(){};

    public Account(Long accountId, int user_id, BigDecimal balance) {
        this.accountId = accountId;
        this.balance = balance;
        this.user_id = user_id;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
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
