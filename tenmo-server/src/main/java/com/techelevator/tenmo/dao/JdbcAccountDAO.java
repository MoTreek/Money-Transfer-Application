package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcAccountDAO implements AccountDAO {

    private JdbcTemplate jdbcTemplate;
    private BigDecimal balance;

    public JdbcAccountDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    //Get account by userID
    @Override
    public Account getAccount(int user_id) {
        Account account = null;
        String sql = "SELECT account_id, user_id, balance FROM account WHERE user_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, user_id);
        if (results.next()) {
            account = mapRowToAccount(results);
        }
        return account;
    }

    //List all account IDs (Checked)
    @Override
    public List<Account> getAllAccounts() {
        List<Account> accounts = new ArrayList<>();
        String sql = "SELECT account_id,  user_id, balance FROM account;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while (results.next()) {
            accounts.add(mapRowToAccount(results));
        }
        return accounts;
    }

    @Override
    public Double getAccountBalance(int user_id) {
        System.out.println("user id ? " + user_id);
        double balance = 0;
        String sql = "SELECT balance FROM account WHERE user_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, user_id);
        if (results.next()) {
            balance = results.getDouble("balance");
        }
        return balance;
    }


    //Updates an account balance?
    @Override
    public Account updateAccount(Account account) {
        Account result = account;
        String sql = "UPDATE account " +
                "SET balance = ? WHERE account_id = ?";
        jdbcTemplate.update(sql, account.getBalance(), account.getAccount_id());

        return result;
    }


    public Account mapRowToAccount(SqlRowSet rs) {
        Account account = new Account();
        account.setAccount_id(rs.getInt("account_id"));
        account.setUser_id(rs.getInt("user_id"));
        account.setBalance(rs.getDouble("balance"));

        return account;
    }

}
