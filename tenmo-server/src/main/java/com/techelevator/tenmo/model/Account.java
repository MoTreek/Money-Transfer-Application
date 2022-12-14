package com.techelevator.tenmo.model;

import java.math.BigDecimal;

public class Account {
    private int account_id;
    private Double balance;
    private int user_id;

    public Account() {
    }



    public Account(int account_id, int user_id, Double balance) {
        this.account_id = account_id;
        this.user_id = user_id;
        this.balance = balance;

    }

    public int getAccount_id() {
        return account_id;
    }

    public void setAccount_id(int account_id) {
        this.account_id = account_id;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    @Override
    public String toString() {
        return "Transfer{" + "id=" + user_id + ", Type='" + balance + '\'' + ", Status=" + user_id + '}';
    }

}
