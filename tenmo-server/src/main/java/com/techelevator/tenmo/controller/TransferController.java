package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.dao.AccountDAO;
import com.techelevator.tenmo.dao.TransferDAO;
import com.techelevator.tenmo.dao.UserDao;
import com.techelevator.tenmo.model.Transfer;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TransferController {
    private AccountDAO accountDAO;
    private TransferDAO transferDAO;
    private UserDao userDao;

    public TransferController(AccountDAO accountDAO, TransferDAO transferDAO, UserDao userDao) {
        this.accountDAO = accountDAO;
        this.transferDAO = transferDAO;
        this.userDao = userDao;
    }

    //This one works!
    @RequestMapping(path = "/transfers/{accountID}", method = RequestMethod.GET)
    public List<Transfer> getListTransfersByAccountID(@PathVariable int accountID){
        return transferDAO.getListTransfers(accountID);
    }

    //This one works!
    @RequestMapping(path = "/transfer/{transferID}", method = RequestMethod.GET)
    public Transfer getTransferByTransferID(@PathVariable int transferID){
        return transferDAO.getTransfer(transferID);
    }


    @RequestMapping(path = "/transfers/{transferID}", method = RequestMethod.PUT)
    public boolean updateTransferStatus(@RequestBody Transfer transfer, @PathVariable int transferID){
        boolean success = false;
        try{
            transferDAO.updateTransferStatusID(transfer);
            success = true;
        } catch (Exception e) {}
            return success;

    }


    @RequestMapping(path = "/transfers/", method = RequestMethod.POST)
    public Transfer createTransfer(@RequestBody Transfer transfer){
            return transferDAO.createTransfer(transfer);
    }


}
