package com.api.inventorysystem.DTO;
import com.api.inventorysystem.Entity.Role;
import lombok.Data;

@Data
public class UserDTO {

    private Long id;
    private String name;
    private String email;
    private String password;
    private Role role;

}
