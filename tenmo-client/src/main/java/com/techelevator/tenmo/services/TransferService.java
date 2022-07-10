package com.techelevator.tenmo.services;

import com.techelevator.tenmo.model.*;
import com.techelevator.util.BasicLogger;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientResponseException;

public class TransferService extends AuthenticatedAPIService {


    /*public Transfer createTransfer(TransferDTO dto) {
        Transfer transfer = null;
        transfer.setAmount(dto.getAmount());
        transfer.setAccount_from(dto.getFromUserId());
        transfer.setAccount_to(dto.getToUserId());
        transfer.setTransfer_type_id(dto.getTransferTypeId());
        return transfer;
    }*/

    public Transfer createTransfer(TransferDTO dto) {
        Transfer transfer = null;
        try {
            ResponseEntity<Transfer> response =
                    restTemplate.exchange(API_BASE_URL, HttpMethod.POST, makeTransferDtoEntity(dto), Transfer.class);
            transfer = response.getBody();
        } catch (RestClientResponseException | ResourceAccessException e) {
            BasicLogger.log(e.getMessage());
        }
        return transfer;
    }

    public Transfer[] listTransfersByAccount(Account accountID) {
        Integer account_id = accountID.getAccount_id();
        Transfer[] transfers = null;
        try {
            ResponseEntity<Transfer[]> response = restTemplate.exchange(API_BASE_URL + "transfers/" + account_id, HttpMethod.GET, makeAuthEntity(), Transfer[].class);
            transfers = response.getBody();
                    //restTemplate.getForObject(API_BASE_URL + "transfers/" + accountID, Transfer[].class);
        } catch (RestClientResponseException | ResourceAccessException e) {
            BasicLogger.log(e.getMessage());
        }
        return transfers;
    }
}

    /*private Object makeTransferDtoEntity(TransferDTO dto) {
    }
}*/





