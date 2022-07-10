package com.techelevator.tenmo.services;

import com.techelevator.tenmo.model.TransferDTO;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

public class AuthenticatedAPIService {

    protected String API_BASE_URL = "http://localhost:8080/";
    protected final RestTemplate restTemplate = new RestTemplate();

    protected String authToken = null;

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    protected HttpEntity<Void> makeAuthEntity() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(authToken);
        return new HttpEntity<>(headers);
    }


    //Make a post HTTP entity that uses Transfer DTO dto as a parameter
    protected HttpEntity<Void> makeTransferDtoEntity(TransferDTO dto) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(authToken);
        return new HttpEntity<>(headers);
    }
}
