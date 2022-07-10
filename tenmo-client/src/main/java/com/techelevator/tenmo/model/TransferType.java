package com.techelevator.tenmo.model;

import java.net.Authenticator;

public class TransferType {
    private int transfer_type_id;
    private String transfer_type_desc;

    public TransferType() {
    }

    public int getTransfer_type_id() {
        return transfer_type_id;
    }

    public void setTransfer_type_id(int transfer_type_id) {
        this.transfer_type_id = transfer_type_id;
    }

    public String getTransfer_type_desc() {
        return transfer_type_desc;
    }

    public void setTransfer_type_desc(String transfer_type_desc) {
        this.transfer_type_desc = transfer_type_desc;
    }

    @Override
    public String toString() {
        return "TransferType{\n" + "transfer_type_id =" + transfer_type_id + ", transfer_type_desc =" + transfer_type_desc + "}";
    }
}