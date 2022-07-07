package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;

import javax.security.auth.login.AccountNotFoundException;
import java.math.BigDecimal;
import java.util.List;

public interface AccountDAO {

    public Account getAccountByUserID(int userID);
    public List<Account> listAll();
    public Account updateAccount(Account account);

}

