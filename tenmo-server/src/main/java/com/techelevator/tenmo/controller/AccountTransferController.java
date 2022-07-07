package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.dao.AccountDAO;
import com.techelevator.tenmo.dao.TransferDAO;
import com.techelevator.tenmo.dao.UserDao;
import com.techelevator.tenmo.model.Account;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import javax.security.auth.login.AccountNotFoundException;
import java.util.List;

@RestController
public class AccountTransferController {
    private AccountDAO accountDAO;
    private TransferDAO transferDAO;
    private UserDao userDao;

    public AccountTransferController(AccountDAO accountDAO, TransferDAO transferDAO, UserDao userDao) {
        this.accountDAO = accountDAO;
        this.transferDAO = transferDAO;
        this.userDao = userDao;
    }
    //ACCOUNT METHODS

    //Get Account balance with accountID

    //Get Account object associated with userID
    @RequestMapping(path = "/account/{id}", method = RequestMethod.GET)
    public Account get(@PathVariable int id) {
        return accountDAO.getAccountByUserID(id);
    }

    //List of all Accounts
    @RequestMapping(path = "/accounts", method = RequestMethod.GET)
    public List<Account> listAccounts() {
        return accountDAO.listAll();
    }

    //Update Account associated with accountID
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(path ="/accounts/{id}", method = RequestMethod.PUT)
    public boolean updateAccount(@RequestBody Account account, @PathVariable int accountID) throws AccountNotFoundException {

        boolean success = false;
        try{
            accountDAO.updateAccount(account);
            success = true;
        } catch (Exception e) {}
        return success;
    }

    //TRANSFER METHODS

    //Get list of Transfers associated with a user_id

    //Get individual Transfer associated a transfer_id

    //Update transferStatusID associated with a transfer_id

    //Create new Transfer with passed in parameters transfer_type_id, transfer_status_id, account_from, account_to, amount

}
