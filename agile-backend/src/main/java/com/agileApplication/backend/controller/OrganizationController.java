package com.agileApplication.backend.controller;

import com.agileApplication.backend.enums.Subscription;
import com.agileApplication.backend.jwtConfig.JwtUtil;
import com.agileApplication.backend.models.Organization;
import com.agileApplication.backend.requestDTO.LoginRequestDTO;
import com.agileApplication.backend.requestDTO.OrgRequestDTO;
import com.agileApplication.backend.service.OrgService;
import com.agileApplication.backend.util.PaymentApi;
import io.jsonwebtoken.io.IOException;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("agile/v1/org")
public class OrganizationController {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    OrgService orgService;

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    PaymentApi paymentApi;



//    //  Organization Registration with Subscription
//    @PostMapping("/registration")
//    public ResponseEntity<String> orgRegistration(@RequestBody OrgRequestDTO request,
//                                                  @RequestParam int amount,
//                                                  @RequestParam String currency) {
//
//
//        try {
//            // 🔹 Step 1: Check if organization already exists
//            if (orgService.isOrganizationExists(request.getEmail())) {
//                return new ResponseEntity<>("Organization already exists! Redirect to login.", HttpStatus.FOUND);
//            }
//
//            // 🔹 Step 2: Validate subscription plan
//            if (request.getSubscriptionPlan() == null) {
//                return new ResponseEntity<>("Please select a subscription plan before registering.", HttpStatus.BAD_REQUEST);
//            }
//
//            // 🔹 Step 3: If PRO or PREMIUM, process payment
//            if (request.getSubscriptionPlan() == Subscription.PRO ||
//                    request.getSubscriptionPlan() == Subscription.PREMIUM) {
//
//                ResponseEntity<String> paymentResponse = paymentApi.payment(amount, currency);
//
//                if (!paymentResponse.getStatusCode().is2xxSuccessful()) {
//                    return new ResponseEntity<>("Payment failed! Try again.", HttpStatus.BAD_REQUEST);
//                }
//            }
//
//            // 🔹 Step 4: Register organization after payment success
//            orgService.orgRegistration(request);
//            return new ResponseEntity<>("Registration successful!", HttpStatus.CREATED);
//
//        } catch (Exception e) {
//            return new ResponseEntity<>("Registration failed", HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }

    @PostMapping("/register")
    public ResponseEntity<String> orgRegister(@RequestBody OrgRequestDTO orgRequestDTO){
//        System.out.println(organization.getEmail());
//        System.out.println(organization.getId());
//        System.out.println(organization.getOrgName());
//        System.out.println(organization.getPassword());

        String url = "http://localhost:8081/api/db/org/register";
        RequestEntity request = RequestEntity.post(url).body(orgRequestDTO);
        ResponseEntity<String> responseDB = restTemplate.exchange(url, HttpMethod.POST, request, String.class);
        System.out.println(responseDB);
        return ResponseEntity.ok("Organization registered successfully");
    }

    //  User Login with JWT Token
    @PostMapping("/login")
    public ResponseEntity<String> userLogin(@RequestBody LoginRequestDTO loginRequestDTO) throws IOException {
        if (orgService.userLogin(loginRequestDTO)) {
            String token = jwtUtil.generateToken(loginRequestDTO.getUsername());
            System.out.println(token);
            return new ResponseEntity<>(token, HttpStatus.OK);
        }
        return new ResponseEntity<>("Invalid Credentials", HttpStatus.UNAUTHORIZED);
    }

    //  JWT Token Verification
    @GetMapping("/test")
    public String test(@RequestHeader("Authorization") String authHeader) {
        String token = authHeader.substring(7);
        System.out.println(token);
        String username = jwtUtil.extractUsername(token);
        System.out.println(username);
        System.out.println("endpoint hi hua");
        if (jwtUtil.validateToken(token, username)) {
            return username + " testing verified";
        }
        return "testing failed";
    }

}





