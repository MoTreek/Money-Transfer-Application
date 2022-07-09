package com.techelevator.tenmo.services;

import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.AuthenticatedUser;
import com.techelevator.tenmo.model.Transfer;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

public class AccountService extends AuthenticatedAPIService{
    public AccountService() {
    }


    public Double getBalance(AuthenticatedUser currentUser) {
        Double balance = null;
        int user_id = currentUser.getUser().getUser_id();
       try  {
            ResponseEntity<Double> response = restTemplate.exchange(API_BASE_URL + "account/balance/" + user_id, HttpMethod.GET, makeAuthEntity(), Double.class);
            balance = response.getBody();

        } catch(RestClientResponseException | ResourceAccessException e){
           System.out.println(e.getCause());
           System.out.println(e.getMessage());

        }
        return balance;
    }

}


