package com.eeat.apiauth.DTO;

import lombok.Data;

@Data
public class LoginDTO {
    private String login;
    private String password;
    private String token;
}
