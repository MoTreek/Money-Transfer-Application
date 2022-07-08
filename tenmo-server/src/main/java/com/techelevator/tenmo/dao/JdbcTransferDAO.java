package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Transfer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcTransferDAO implements TransferDAO {

    //View pending transfer and status

    //TODO

    //Find All transfers (FINISH) Is finding all transfers by account_id sufficient?

    //Add or Create Transfer

    //Get Transfer for user

    //Set status


    private  JdbcTemplate jdbcTemplate;
    private BigDecimal bigDecimal;

    public void JdbcTransferDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

//    public List<Transfer> listAll(){
//    return List<Transfer> transfers;
//    }

    Transfer transfers = new Transfer();


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

    @Override
    public Transfer updateTransferStatusID(Transfer transfer) {
        return null;
    }
//This is wrong, brain was melting, can we do a join on an update method? Reminder to review update method Data Access and DAO lecture
    //@Override
    /*public Transfer updateTransferStatusID(Transfer transfer) {
        Transfer updatedTransfer = new Transfer();
        String sql = "UPDATE transfer " +
                "SET transfer_status_id = ? " +
                "WHERE transfer_id = ?;";
        jdbcTemplate.update(sql,Transfer.class, transfer.getTransfer_status_id(), transfer.getTransfer_id());
             //   transfer.getTransfer_status_id(), transfer.getAccount_from(), transfer.getAccount_to(), transfer.getAccount_from(), transfer.getAmount();
        return updatedTransfer;
    }*/

    @Override
    public Transfer createTransfer(Transfer newTransfer) {
        String sql = "INSERT INTO transfer (transfer_type_id, transfer_status_id, account_from, account_to, amount) VALUES (?, ?, ?, ?, ?) RETURNING transfer_id;";
        Integer newId = jdbcTemplate.queryForObject(sql, Integer.class,
                newTransfer.getTransfer_type_id(), newTransfer.getTransfer_status_id(), newTransfer.getAccount_from(), newTransfer.getAccount_to(), newTransfer.getAmount());
        return getTransferByTransferID(newId);
    }


    //Check to see if sufficient for new sql statements
    private Transfer mapRowToTransfer(SqlRowSet rs) {
        Transfer transfer = new Transfer();
        transfer.setTransfer_id(rs.getInt("transfer_id"));
        transfer.setTransfer_type_id(rs.getInt("transfer_type_id"));
        transfer.setTransfer_status_id(rs.getInt("transfer_status_id"));
        transfer.setAccount_from(rs.getInt("account_from"));
        transfer.setAccount_to(rs.getInt("account_to"));
        transfer.setAmount(rs.getBigDecimal("amount"));
        return transfer;
    }


}
