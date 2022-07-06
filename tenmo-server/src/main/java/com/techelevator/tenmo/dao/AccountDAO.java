package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;

import javax.security.auth.login.AccountNotFoundException;
import java.math.BigDecimal;
import java.util.List;

public interface AccountDAO {

    Account getAccountByAccountID(Integer user_id);

    List<Account> listAll();

    Account updateAccount(Account account, Long id) throws AccountNotFoundException;
}

