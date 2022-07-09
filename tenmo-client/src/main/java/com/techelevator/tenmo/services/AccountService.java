package com.techelevator.tenmo.services;

import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.AuthenticatedUser;
import org.apiguardian.api.API;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientResponseException;

public class AccountService extends AuthenticatedAPIService{
    public AccountService() {
    }


    public Double getBalance(AuthenticatedUser currentUser) {
        Double balance = null;
        int user_id = currentUser.getUser().getId();
       try  {
            ResponseEntity<Double> response = restTemplate.exchange(API_BASE_URL + "account/balance/" + user_id, HttpMethod.GET, makeAuthEntity(), Double.class);
            balance = response.getBody();

        } catch(RestClientResponseException | ResourceAccessException e){
           System.out.println(e.getCause());
           System.out.println(e.getMessage());

        }
        return balance;
    }

    public Account getAccount(AuthenticatedUser currentUser) {
        Account account = null;
        int user_id = currentUser.getUser().getId();
        try {
            ResponseEntity<Account> response = restTemplate.exchange(API_BASE_URL + "account/" + user_id, HttpMethod.GET, makeAuthEntity(), Account.class);
            account = response.getBody();
        } catch (RestClientResponseException | ResourceAccessException e) {
            System.out.println(e.getCause());
            System.out.println(e.getMessage());
        }
        return account;
    }


}


