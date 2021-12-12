package com.hackathon.api.domain.security;

import lombok.Data;

@Data
public class UserCredentials {

    private String username;
    private String password;

}
