package com.techelevator.tenmo.services;

import com.techelevator.tenmo.model.Transfer;
import org.apiguardian.api.API;
import org.springframework.http.ResponseEntity;

public class TransferService extends AuthenticatedAPIService {
    public TransferService() {
    }

    public Transfer createTransfer(Transfer transfer) {
Transfer transfer = null;
try {
    ResponseEntity<Transfer> response = restTemplate.exchange(API_BASE_URL + "transfers/")
}
    }

    public Transfer updatePendingTransferStatus() {
        return null;
    }

    public Transfer retrieveTransferDetails() {
        return null;
    }


}

