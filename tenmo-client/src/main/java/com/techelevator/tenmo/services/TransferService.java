package com.techelevator.tenmo.services;

import com.techelevator.tenmo.model.Transfer;
import com.techelevator.tenmo.model.TransferDTO;
import org.apiguardian.api.API;
import org.springframework.http.ResponseEntity;

public class TransferService extends AuthenticatedAPIService {


    public Transfer createTransfer(TransferDTO dto) {
        Transfer transfer = null;
        transfer.setAmount(dto.getAmount());
        transfer.setAccount_from(dto.getFromUserId());
        transfer.setAccount_to(dto.getToUserId());
        transfer.setTransfer_type_id(dto.getTransferTypeId());
        return transfer;
    }
}



//

