package com.example.fullapp_spring3.dtos;

import com.example.fullapp_spring3.models.Role;
import lombok.Data;

@Data
public class UserDTO {

    private int id;
    private String username;
    private String firstname;
    private String surname;
    private String email;
    private String phoneNumber;
    private Role role;

    public UserDTO(int id, String username, String firstname, String surname, String email, String phoneNumber, Role role) {
        this.id = id;
        this.username = username;
        this.firstname = firstname;
        this.surname = surname;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.role = role;
    }
}
