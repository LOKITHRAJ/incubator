package com.app.incubator.framework.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RestClientService {


    @Autowired
    RestTemplate restTemplate;


    public String getForResponseEntity(String url) {

        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        return response.getBody();
    }


}
