package com.techelevator.tenmo.model;

import java.math.BigDecimal;

public class Account {
    private int accountID;
    private BigDecimal balance;
    private int user_ID;

    public Account() {
    }

    ;

    public Account(int accountID, int user_ID, BigDecimal balance) {
        this.accountID = accountID;
        this.balance = balance;
        this.user_ID = user_ID;
    }

    public int getAccountID() {
        return accountID;
    }

    public void setAccountID(int accountID) {
        this.accountID = accountID;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public int getUser_ID() {
        return user_ID;
    }

    public void setUser_ID(int user_ID) {
        this.user_ID = user_ID;
    }

    @Override
    public String toString() {
        return "Transfer{" +
                "id=" + accountID +
                ", Type='" + balance + '\'' +
                ", Status=" + user_ID + '}';
    }

}
