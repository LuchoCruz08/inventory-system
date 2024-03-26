package com.api.inventorysystem.Security.Auth;
import lombok.Data;

@Data
public class AuthRequest {

    String email;
    String password;

}
