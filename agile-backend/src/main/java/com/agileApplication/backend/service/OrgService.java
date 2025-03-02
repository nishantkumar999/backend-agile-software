package com.agileApplication.backend.service;


import com.agileApplication.backend.requestDTO.LoginRequestDTO;
import com.agileApplication.backend.requestDTO.OrgRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OrgService {

    @Autowired
    private  RestTemplate restTemplate;

    public  final String dbURL = "http://localhost:8081/api/db/org";

    //  Check if organization exists
    public boolean isOrganizationExists(String email) {
        String url = dbURL + "/exists?email=" + email;
        ResponseEntity<Boolean> response = restTemplate.getForEntity(url, Boolean.class);
        return response.getBody() != null && response.getBody();
    }

    //  Register organization by calling dbapi
    public  void orgRegistration(OrgRequestDTO orgRequestDTO) {
        String url = dbURL + "/register";
        restTemplate.postForEntity(url, orgRequestDTO, String.class);
    }

    //  User login by calling dbapi
    public boolean userLogin(LoginRequestDTO loginRequestDTO) {
        try {
            String url = dbURL + "/login";  // Your dbapi should have this endpoint
            System.out.println("Sending login request to:  " + url);
            System.out.println("Login Request: " + loginRequestDTO.getUsername() + " | " + loginRequestDTO.getPassword());

            ResponseEntity<Boolean> response = restTemplate.postForEntity(url, loginRequestDTO, Boolean.class);

            System.out.println(" Response from DB API:: " + response.getBody());

            return response.getBody() != null && response.getBody();
        } catch (Exception e) {
            System.out.println("Login failed: " + e.getMessage());
//            e.printStackTrace();  // Print the full error stack
            return false;
        }
    }
}
