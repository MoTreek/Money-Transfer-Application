package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Transfer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.util.ArrayList;
import java.util.List;

public class JdbcTransferDAO implements TransferDAO {

    //View pending transfer and status


//Transfer method take 2 accounts as objects, transfer from account and another as parameters
//third parameter as amount.

    //TODO

    //Find All transfers (FINISH)

    //Find individual transfer by Transfer_ID

    //Add or Create Transfer

    //Get Transfer for user

    //Set status


private static JdbcTemplate jdbcTemplate;
public void JdbcTransferDao(JdbcTemplate jdbcTemplate) {this.jdbcTemplate = jdbcTemplate;}

//    public List<Transfer> listAll(){
//    return List<Transfer> transfers;
//    }

    Transfer transfers = new Transfer();


    //Todo Figure out how to get the join to work to get the account from/to actual username
    @Override
    public List<Transfer> listAll() {
        List<Transfer> transfers = new ArrayList<>();
        String sql = "SELECT transfer.transfer_id" +
                " account_from, account_to" +
                " transfer.amount  FROM transfer;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while (results.next()) {
            Transfer transfer = mapRowToTransfer(results);
            transfers.add(transfer);
        }
        return transfers;
    }

    @Override
    public Transfer getTransferById(Integer transferId) {
        String sql = "SELECT transfer.transfer_id" +
                " account_from, account_to, transfer_type, transfer_status " +
                " transfer.amount  FROM transfer WHERE transfer_id = ?;";
        Transfer transfer = jdbcTemplate.queryForObject(sql, Transfer.class, transferId);
        if (transferId != null) {
            return transfer;
        } else {
            System.out.println("Account does not exist.");
            return null;
        }
    }

    @Override
    public Transfer create(Transfer transfer, int transferId) {
        transfer.setTransferId(getMaxIdPlusOne());
//        ???????????????????????????????????????????
        return transfer;
    }

    private int getMaxID() {
        int maxID = 0;

        for (Transfer r : transfers) {
            if (r.getTransferId() > maxID) {
                maxID = r.getTransferId();
            }
        }
        return maxID;
    }
    private int getMaxIdPlusOne() {
        return getMaxID() + 1;
    }



    private Transfer mapRowToTransfer(SqlRowSet rs) {
        Transfer transfer = new Transfer();
        transfer.setTransferId(rs.getInt("transfer_id"));
        transfer.setTransferTypeId(rs.getInt("transfer_type_id"));
        transfer.setTransferStatus(rs.getInt("transfer_status_id"));
        transfer.setAccountFrom(rs.getInt("account_from"));
        transfer.setAccountTo(rs.getInt("account_in"));
        transfer.setAmount(rs.getBigDecimal("amount"));
        return transfer;
    }


}
