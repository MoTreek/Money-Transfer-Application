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

    //Find All transfers (FINISH) Is finding all transfers by account_id sufficient?

    //Add or Create Transfer

    //Get Transfer for user

    //Set status


    private static JdbcTemplate jdbcTemplate;

    public void JdbcTransferDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

//    public List<Transfer> listAll(){
//    return List<Transfer> transfers;
//    }

    Transfer transfers = new Transfer();


    //Todo
    //(Updated: Check SQL Statement)
    @Override
    public List<Transfer> getListTransfersByUserID(Integer userID) {
        List<Transfer> transfers = new ArrayList<>();
        String sql = "SELECT  transfer.account_from, \n" +
                "        transfer.account_to,\n" +
                "        account.balance,\n" +
                "        tenmo_user.username\n" +
                "FROM transfer\n" +
                "JOIN account\n" +
                "    ON transfer.account_from = account.account_id\n" +
                "JOIN tenmo_user\n" +
                "    ON account.user_id = tenmo_user.user_id" +
                "WHERE tenmo_user.user_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userID);
        while (results.next()) {
            Transfer transfer = mapRowToTransfer(results);
            transfers.add(transfer);
        }
        return transfers;
    }

    //(Updated: Check SQL Statement)
    @Override
    public Transfer getTransferByTransferID(Integer transferID) {
        String sql = "SELECT  transfer.account_from, \n" +
                "        transfer.account_to,\n" +
                "        account.balance,\n" +
                "        tenmo_user.username\n" +
                "FROM transfer\n" +
                "JOIN account\n" +
                "    ON transfer.account_from = account.account_id\n" +
                "JOIN tenmo_user\n" +
                "    ON account.user_id = tenmo_user.user_id\n" +
                "WHERE transfer.transfer_id = ?;";
        Transfer transfer = jdbcTemplate.queryForObject(sql, Transfer.class, transferID);
        if (transferID != null) {
            return transfer;
        } else {
            System.out.println("Account does not exist.");
            return null;
        }
    }

    //This is wrong, brain was melting, can we do a join on an update method? Reminder to review update method Data Access and DAO lecture
   @Override
    public Transfer updateTransferStatusID(Transfer transfer) {
        Transfer updatedTransfer = new Transfer();
        String sql =    "UPDATE transfer " +
                        "SET transfer_status_id = ? " +
                        "WHERE transfer_id = ?;";
        jdbcTemplate.update(sql, transfer.getTransferId(), transfer.getAccountFrom(),
              transfer.getTransferStatus(), transfer.getAccountFrom(), transfer.getAccountTo(), transfer.getAccountFrom(), transfer.getAmount());
       // jdbcTemplate.update(sql, transfer.getTransferStatus());
        return updatedTransfer;
    }

    @Override
    public Transfer createProject(Transfer newTransfer) {
        String sql = "INSERT INTO transfer (transfer_type_id, transfer_status_id, account_from, account_to, amount) VALUES (?, ?, ?, ?, ?) RETURNING transfer_id;";
        Integer newId = jdbcTemplate.queryForObject(sql, Integer.class,
                newTransfer.getTransferTypeId(), newTransfer.getTransferStatus() ,newTransfer.getAccountFrom(), newTransfer.getAccountTo(), newTransfer.getAmount());
        return getTransferByTransferID(newId);
    }


    //Check to see if sufficient for new sql statements
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
