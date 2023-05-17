package com.example.fullapp_spring3.dtos;

import com.example.fullapp_spring3.models.User;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class UserDTOMapper implements Function<User, UserDTO> {

    @Override
    public UserDTO apply(User user) {
        return new UserDTO(
                user.getId(),
                user.getUsername(),
                user.getFirstname(),
                user.getSurname(),
                user.getEmail(),
                user.getPhoneNumber(),
                user.getRole()
        );
    }
}
