package com.techelevator.tenmo.model;

import java.math.BigDecimal;

public class Transfer {
    private int transferID;
    private int transferTypeID;
    private int transferStatusID;
    private int accountFrom;
    private int accountTo;
    private BigDecimal amount;

    public Transfer() {}

    public Transfer(int transferId, int transferTypeID, int transferStatusID, int accountFrom, int accountTo, BigDecimal amount) {
        this.transferID = transferID;
        this.transferTypeID = transferTypeID;
        this.transferStatusID = transferStatusID;
        this.accountFrom = accountFrom;
        this.accountTo = accountTo;
        this.amount = amount;
    }

    public int getTransferId() {
        return transferID;
    }

    public void setTransferId(int transferId) {
        this.transferID = transferID;
    }

    public int getTransferTypeId() {
        return transferTypeID;
    }

    public void setTransferTypeId(int transferTypeId) {
        this.transferTypeID = transferTypeID;
    }

    public int getTransferStatus() {
        return transferStatusID;
    }

    public void setTransferStatus(int transferStatus) {
        this.transferStatusID = transferStatus;
    }

    public int getAccountFrom() {
        return accountFrom;
    }

    public void setAccountFrom(int accountFrom) {
        this.accountFrom = accountFrom;
    }

    public int getAccountTo() {
        return accountTo;
    }

    public void setAccountTo(int accountTo) {
        this.accountTo = accountTo;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Transfer{" +
                "id=" + transferID +
                ", Type='" + transferTypeID + '\'' +
                ", Status=" + transferStatusID +
                ", Account From=" + accountFrom +
                ", Account To=" + accountTo +
                ", Amount=" + accountFrom +
                '}';
    }

}
