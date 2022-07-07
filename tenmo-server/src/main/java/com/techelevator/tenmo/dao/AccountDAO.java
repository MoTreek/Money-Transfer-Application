package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;

import javax.security.auth.login.AccountNotFoundException;
import java.math.BigDecimal;
import java.util.List;

public interface AccountDAO {

    int getAccountIDByUserID(Integer user_id);

    List<Account> listAll();

    //Updates an account
    Account updateAccount(Account account);
}

