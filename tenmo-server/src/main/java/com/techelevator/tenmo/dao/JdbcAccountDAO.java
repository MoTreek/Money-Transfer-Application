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

    private  JdbcTemplate jdbcTemplate;
    private BigDecimal balance;

    public JdbcAccountDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    //fix to make get
    @Override
    public Account getAccountByUserID(int id) {
        Account account = null;
        String sql = "SELECT account_id, user_id, balance FROM account WHERE user_id = ?";
        SqlRowSet sqlRowSet = jdbcTemplate.queryForRowSet(sql, id);
        while (sqlRowSet.next()) {
            account = mapRowToAccount(sqlRowSet);
        }
        return account;
    }

    //List account IDs associated with a given userID (Checked)
    //Should this be made to return a list? Couldn't one user_id have more than one account_id
    //Have not made matching AccountTransferController method yet
    /*@Override
    public int getAccountIDByUserID(Integer userID) {
        String sql = "SELECT account_id FROM account WHERE user_id ILIKE ?;";
        Integer id = jdbcTemplate.queryForObject(sql, Integer.class, userID);
        if (id != null) {
            return id;
        } else {
            System.out.println("Account does not exist.");
            return -1;
        }
    }*/

    //List all account IDs (Checked)
    @Override
    public List<Account> listAll() {
        List<Account> accounts = new ArrayList<>();
        String sql = "SELECT account_id,  user_id, balance FROM account";
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
        jdbcTemplate.update(sql, account.getBalance(), account.getAccountID());

        return result;
    }


    public Account mapRowToAccount(SqlRowSet rs) {
        Account account = new Account();
        account.setAccountID(rs.getInt("account_id"));
        account.setUser_ID(rs.getInt("user_id"));
        account.setBalance(rs.getBigDecimal("balance"));

        return account;
    }

}
