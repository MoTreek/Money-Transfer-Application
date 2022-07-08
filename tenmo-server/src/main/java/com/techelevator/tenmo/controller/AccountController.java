package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.dao.AccountDAO;
import com.techelevator.tenmo.dao.TransferDAO;
import com.techelevator.tenmo.dao.UserDao;
import com.techelevator.tenmo.model.Account;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import javax.security.auth.login.AccountNotFoundException;
import java.math.BigDecimal;
import java.util.List;

@RestController
public class AccountController {
    private AccountDAO accountDAO;
    private TransferDAO transferDAO;
    private UserDao userDao;

    public AccountController(AccountDAO accountDAO, TransferDAO transferDAO, UserDao userDao) {
        this.accountDAO = accountDAO;
        this.transferDAO = transferDAO;
        this.userDao = userDao;
    }
    //ACCOUNT METHODS

    //(Returns balance on postman)
    //@param id is user_id of the user
    //@return all balance info for a given account
    @RequestMapping(path = "/account/balance/{id}", method = RequestMethod.GET)
    public BigDecimal getAccountBalanceByUserID(@PathVariable int id) {
        return accountDAO.getAccountBalance(id);
    }

    //(Returns account_id, balance and user_id on postman)
    //@param id is user_id of the user
    //@return all account info for a given account
    @RequestMapping(path = "/account/{id}", method = RequestMethod.GET)
    public Account getAccountByUserID(@PathVariable int id) {
        return accountDAO.getAccount(id);
    }

    //List of all Accounts (Returns array of account objects on postman)
    @RequestMapping(path = "/accounts", method = RequestMethod.GET)
    public List<Account> listAccounts() {
        return accountDAO.getAllAccounts();
    }

    //Update Account associated with accountID (Not able to test successfully yet, lookup how to format a put in postman)
    //@param id is user_id of the user
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(path ="/accounts/{id}", method = RequestMethod.PUT)
    public boolean updateAccount(@RequestBody Account account, @PathVariable int id) throws AccountNotFoundException {

        boolean success = false;
        try{
            accountDAO.updateAccount(account);
            success = true;
        } catch (Exception e) {}
        return success;
    }

//---------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    //TRANSFER METHODS

    //Get list of Transfers associated with a user_id

    //Get individual Transfer associated a transfer_id

    //Update transferStatusID associated with a transfer_id

    //Create new Transfer with passed in parameters transfer_type_id, transfer_status_id, account_from, account_to, amount

}
