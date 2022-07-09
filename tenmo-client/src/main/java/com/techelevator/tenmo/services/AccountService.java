package com.techelevator.tenmo.services;

import com.techelevator.tenmo.model.AuthenticatedUser;
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

}


