package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;

import javax.security.auth.login.AccountNotFoundException;
import java.math.BigDecimal;
import java.util.List;

public interface AccountDAO {

    public Account getAccount(int userID);


    public List<Account> getAllAccounts();

    public Account updateAccount(Account account);

    public Double getAccountBalance(int user_id);
}

