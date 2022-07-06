package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.security.auth.login.AccountNotFoundException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcAccountDAO implements AccountDAO {

    private static JdbcTemplate jdbcTemplate;
    private BigDecimal balance;

    public void JdbcAccountDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    //List account IDs associated with a given userID
    @Override
    public Account getAccountByAccountID(Integer id) {
        String sql = "SELECT account_id FROM account WHERE user_id ILIKE ?;";
        Account account = jdbcTemplate.queryForObject(sql, Account.class, id);
        if (id != null) {
            return account;
        } else {
            System.out.println("Account does not exist.");
            return null;
        }
    }

    //List all account IDs
    @Override
    public List<Account> listAll() {
        List<Account> accounts = new ArrayList<>();
        String sql = "SELECT account_id FROM account;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while (results.next()) {
            Account account = mapRowToAccount(results);
            accounts.add(account);
        }
        return accounts;
    }

    //Updates an account
    @Override
    public Account updateAccount(Account account) {
        Account result = account;
        String sql = "UPDATE account " +
                "SET balance = ? WHERE account_id = ?";
        jdbcTemplate.update(sql, account.getBalance(), account.getAccountId());

        return result;
    }



    private Account mapRowToAccount(SqlRowSet rs) {
        Account account = new Account();
        account.setAccountId(rs.getLong("account_id"));
        account.setUser_id(rs.getInt("user_id"));
        account.setBalance(rs.getBigDecimal("balance"));
        return account;
    }

}
