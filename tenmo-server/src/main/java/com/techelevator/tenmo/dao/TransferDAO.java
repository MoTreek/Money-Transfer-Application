package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.Transfer;

import java.util.List;

public interface TransferDAO {

    List<Transfer> getListTransfers(Integer userID);
    Transfer getTransfer(Integer transferID);
    Transfer updateTransferStatusID(Transfer transfer);
    Transfer createTransfer(Transfer newTransfer);

    //Create methods for the below two in JdbcTransferDao

   //Transfer subtractFundsFromBalance(Transfer transfer);
   //Transfer addFundsToBalance(Transfer transfer);

    Double subtractFundsFromBalance(Account account);

    Double addFundsToBalance(Account account);

    Integer getFromAccountId(int user_id);

    Integer getToAccountId(int user_id);
}
