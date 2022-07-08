package com.techelevator.tenmo.services;

import com.techelevator.tenmo.model.Transfer;

import java.math.BigDecimal;

public class AccountService extends AuthenticatedAPIService{
    public AccountService() {
    }

    public BigDecimal getBalance() {
        return null;
    }

    public Transfer[] retrieveAllTransfers() {
        Transfer[] transfers = null;
        return transfers;
    }
}
