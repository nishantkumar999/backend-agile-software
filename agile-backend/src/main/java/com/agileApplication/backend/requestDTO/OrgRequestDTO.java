package com.agileApplication.backend.requestDTO;

import com.agileApplication.backend.enums.Subscription;
import lombok.Data;

@Data
public class OrgRequestDTO {


    private String orgName;
    private String email;
    private String password;
    private Subscription subscription;


    public OrgRequestDTO() {
    }

    public OrgRequestDTO(String orgName, String email, String password, Subscription subscription) {
        this.orgName = orgName;
        this.email = email;
        this.password = password;
        this.subscription = subscription;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Subscription getSubscription() {
        return subscription;
    }

    public void setSubscription(Subscription subscription) {
        this.subscription = subscription;
    }
}
