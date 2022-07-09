package com.techelevator.tenmo.model;

import java.math.BigDecimal;

public class TransferDTO {
    public int fromUserId;
    public int toUserId;
    public BigDecimal amount;
    public int transferTypeId;
    public int transferId;
    public int transferStatusId;


    public TransferDTO(int fromUserId, int toUserId, BigDecimal amount, int transferTypeId) {
        this.fromUserId = fromUserId;
        this.toUserId = toUserId;
        this.amount = amount;
        this.transferTypeId = transferTypeId;
    }

    public TransferDTO(int fromUserId, int toUserId, BigDecimal amount, int transferTypeId, int transferId, int transferStatusId) {
        this.fromUserId = fromUserId;
        this.toUserId = toUserId;
        this.amount = amount;
        this.transferTypeId = transferTypeId;
        this.transferId = transferId;
        this.transferStatusId = transferStatusId;
    }

    public int getFromUserId() {
        return fromUserId;
    }

    public void setFromUserId(int fromUserId) {
        this.fromUserId = fromUserId;
    }

    public int getToUserId() {
        return toUserId;
    }

    public void setToUserId(int toUserId) {
        this.toUserId = toUserId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public int getTransferTypeId() {
        return transferTypeId;
    }

    public void setTransferTypeId(int transferTypeId) {
        this.transferTypeId = transferTypeId;
    }
}