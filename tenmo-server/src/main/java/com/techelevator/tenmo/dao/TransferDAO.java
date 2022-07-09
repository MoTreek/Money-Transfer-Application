package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Transfer;

import java.util.List;

public interface TransferDAO {

    List<Transfer> getListTransfers(Integer userID);
    Transfer getTransfer(Integer transferID);
    Transfer updateTransferStatusID(Transfer transfer);
    Transfer createTransfer(Transfer newTransfer);
//    Transfer subtractFundsFromBalance(Transfer transfer);
}
