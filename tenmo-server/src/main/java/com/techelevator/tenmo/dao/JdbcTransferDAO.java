package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.Transfer;
import com.techelevator.tenmo.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

public class JdbcTransferDAO implements TransferDAO {

    //View pending transfer and status


//Transfer method take 2 accounts as objects, transfer from account and another as parameters
//third parameter as amount.

//Approval
private static JdbcTemplate jdbcTemplate;
public void JdbcTransferDao(JdbcTemplate jdbcTemplate) {this.jdbcTemplate = jdbcTemplate;}

    public Transfer create(Transfer transfer) {
        return null;
    }
    public int findByAccountId(int ID) {
        //String sql = "SELECT user_id FROM tenmo_user WHERE username ILIKE ?;";
       // Integer id = jdbcTemplate.queryForObject(sql, Integer.class, username);
//        if (id != null) {
//            return id;
//        } else {
//            return -1;
//        }
        return 0;
    }

    public Boolean updateStatus(Transfer transfer){
        return true;
    };





}
