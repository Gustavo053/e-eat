package com.eeat.userservice.DTO;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserCredentialsDTO {
    private String login;
    private String password;
    private Boolean enable;
}
