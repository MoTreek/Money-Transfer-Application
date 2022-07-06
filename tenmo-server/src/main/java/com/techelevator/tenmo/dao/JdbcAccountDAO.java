package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;
import org.springframework.jdbc.core.JdbcTemplate;

import java.math.BigDecimal;
import java.util.List;

public class JdbcAccountDAO implements AccountDAO {

    private static JdbcTemplate jdbcTemplate;
    private BigDecimal balance;

    public void JdbcAccountDao(JdbcTemplate jdbcTemplate) {this.jdbcTemplate = jdbcTemplate;}

    @Override
    public Account get(int id) {
        return null;
    }

    @Override
    public List<Account> list() {
        return null;
    }

    @Override
    public Account update(Account account) {
        return null;
    }

    //NOT DONE YET

}
