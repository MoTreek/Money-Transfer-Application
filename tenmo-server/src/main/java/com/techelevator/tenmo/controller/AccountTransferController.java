package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.dao.AccountDAO;
import com.techelevator.tenmo.dao.TransferDAO;
import com.techelevator.tenmo.model.Account;
import org.springframework.web.bind.annotation.*;

import javax.print.DocFlavor;
import javax.security.auth.login.AccountNotFoundException;
import java.util.List;

@RestController
public class AccountTransferController {
    private AccountDAO accountDAO;
    private TransferDAO transferDAO;

    public AccountTransferController(AccountDAO accountDAO, TransferDAO transferDAO) {
        this.accountDAO = accountDAO;
        this.transferDAO = transferDAO;
    }
    //ACCOUNT METHODS

    //Get Account Object By USER ID
    @RequestMapping(path = "/account/{id}", method = RequestMethod.GET)
    public Account get(@PathVariable int user_id) {
        return accountDAO.getAccountByUserID(user_id);
    }

    //List of all Accounts
    @RequestMapping(path = "/account", method = RequestMethod.GET)
    public List<Account> listAccounts() {
        return accountDAO.listAll();
    }

    //Update Account 
    @RequestMapping(path ="/account/{id}", method = RequestMethod.PUT)
    public boolean updateAccount(@RequestBody Account account, @PathVariable Long accountID) throws AccountNotFoundException {

        boolean success = false;
        try{
            accountDAO.updateAccount(account);
            success = true;
        } catch (Exception e) {}
        return success;
    }



}
