package br.com.serverest.pojos.responses;

import lombok.Data;

@Data
public class LoginError {
    private String email;
    private String password;
}
