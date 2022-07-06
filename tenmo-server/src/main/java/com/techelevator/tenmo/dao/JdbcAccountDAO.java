package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.User;
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
    public Account updateAccount(Account account, Long id) throws AccountNotFoundException {
        Account result = account;
        boolean finished = false;
        List<Account> accounts = listAll();

        for (int i = 0; i < accounts.size(); i++) {
            if (accounts.get(i).getId() == id) {
                if (result.getId() == 0) {
                    result.setId(id);
                }
                accounts.set(i, result);
                finished = true;
                break;
            }
        }
        if (!finished) {
            throw new AccountNotFoundException();
        }

        return result;
    }

    private Account mapRowToAccount(SqlRowSet rs) {
        Account account = new Account();
        account.setId(rs.getLong("user_id"));
        account.setBalance(rs.getBigDecimal("balance"));
        return account;
    }

}
