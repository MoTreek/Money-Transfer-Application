package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;
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


    private JdbcTemplate jdbcTemplate;
    private BigDecimal bigDecimal;

    public JdbcTransferDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    Transfer transfers = new Transfer();

    @Override
    public List<Transfer> getListTransfers(Integer accountID) {
        List<Transfer> transfers = new ArrayList<>();
        String sql = "SELECT  transfer.transfer_id, transfer.transfer_type_id, transfer.transfer_status_id, transfer.account_from, transfer.account_to, transfer.amount " +
                "FROM transfer " +
                "JOIN account" +
                "    ON transfer.account_from = account.account_id " +
                "WHERE account.account_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, accountID);
        while (results.next()) {
            Transfer transfer = mapRowToTransfer(results);
            transfers.add(transfer);
        }
        return transfers;
    }

    //(Updated: Check SQL Statement)
    //Do not use the query for object use query for Rowset
    @Override
    public Transfer getTransfer(Integer transferID) {
        String sql = "SELECT  transfer.account_from, " +
                "        transfer.account_to, " +
                "        transfer.amount, " +
                "        transfer.transfer_id, " +
                "        transfer.transfer_status_id, " +
                "        transfer.transfer_type_id " +
                "FROM transfer " +
                "WHERE transfer.transfer_id = ?;";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, transferID);
        Transfer newTransfer = null;
        if (rowSet.next()) {
            int transferId = rowSet.getInt("transfer_id");
            int transferAccountFrom = rowSet.getInt("account_from");
            int transferAccountTo = rowSet.getInt("account_to");
            int transferTypeID = rowSet.getInt("transfer_type_id");
            int transferStatusID = rowSet.getInt("transfer_status_id");
            BigDecimal transferBalance = rowSet.getBigDecimal("amount");
            newTransfer = new Transfer(transferId, transferTypeID, transferStatusID, transferAccountFrom, transferAccountTo, transferBalance);
        }
        return newTransfer;
    }

    @Override
    public Transfer updateTransferStatusID(Transfer transfer) {

//This is wrong, brain was melting, can we do a join on an update method? Reminder to review update method Data Access and DAO lecture
        Transfer updatedTransfer = new Transfer();
        String sql = "UPDATE transfer " +
                "SET transfer_status_id = ? " +
                "WHERE transfer_id = ?;";
        jdbcTemplate.update(sql, Transfer.class, transfer.getTransfer_id(), transfer.getTransfer_type_id(), transfer.getTransfer_status_id(), transfer.getAccount_from(), transfer.getAccount_to(), transfer.getAmount());
        return updatedTransfer;
    }


    @Override
    public Transfer createTransfer(Transfer newTransfer) {
        String sql = "INSERT INTO transfer (transfer_type_id, transfer_status_id, account_from, account_to, amount) VALUES (?, ?, ?, ?, ?) RETURNING transfer_id;";
        Integer transferID = jdbcTemplate.queryForObject(sql, Integer.class,
                newTransfer.getTransfer_type_id(), newTransfer.getTransfer_status_id(), newTransfer.getAccount_from(), newTransfer.getAccount_to(), newTransfer.getAmount());
        return getTransfer(transferID);
    }

//    @Override
//    public Transfer subtractFundsFromBalance(Account account) {
//        BigDecimal newBalance =  account.getBalance(account);
//        newBalance -= transfers.getAmount(account);
//
//    }


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
