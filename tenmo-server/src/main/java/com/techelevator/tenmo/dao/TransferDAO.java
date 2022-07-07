package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Transfer;

import java.util.List;

public interface TransferDAO {

    List<Transfer> getListTransfersByUserID(Integer userID);

    Transfer getTransferByTransferID(Integer transferID);
    void updateTransferStatusID(Transfer transfer);
}
