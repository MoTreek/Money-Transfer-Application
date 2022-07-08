package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.Transfer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

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

    //Get account by userID
    @Override
    public Account getAccountByUserID(int userID) {
        Account account = null;
        String sql = "SELECT account_id, user_id, balance FROM account WHERE user_id = ?";
        SqlRowSet sqlRowSet = jdbcTemplate.queryForRowSet(sql, userID);
        while (sqlRowSet.next()) {
            account = mapRowToAccount(sqlRowSet);
        }
        return account;
    }

    @Override
    public BigDecimal getAccountBalanceByUserID(Integer user_id) {
        String sql = "SELECT balance FROM account WHERE user_id = ?";
        BigDecimal accountBalance = jdbcTemplate.queryForObject(sql, BigDecimal.class, user_id);
        if (user_id != null) {
            return accountBalance;
        } else {
            System.out.println("Account does not exist.");
            return null;
        }
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
        jdbcTemplate.update(sql, account.getBalance(), account.getAccount_id());

        return result;
    }




    public Account mapRowToAccount(SqlRowSet rs) {
        Account account = new Account();
        account.setAccount_id(rs.getInt("account_id"));
        account.setUser_id(rs.getInt("user_id"));
        account.setBalance(rs.getBigDecimal("balance"));

        return account;
    }

}
