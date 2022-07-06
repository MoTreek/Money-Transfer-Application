package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;

import java.math.BigDecimal;
import java.util.List;

public interface AccountDAO {

    Account get(int user_id);

    List<Account> list();

    Account update(Account account);


}
