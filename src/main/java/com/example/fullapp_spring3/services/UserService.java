package com.example.fullapp_spring3.services;

import com.example.fullapp_spring3.daos.UserDAO;
import com.example.fullapp_spring3.dtos.UserDTO;
import com.example.fullapp_spring3.dtos.UserDTOMapper;
import com.example.fullapp_spring3.models.User;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserDAO userDAO;
    private final ModelMapper modelMapper;
    private final UserDTOMapper userDTOMapper;


    public UserDTO findUser(String username) {
        if(username == null || username.equals("   ") || username.equals("")){
            throw new IllegalArgumentException("Username is not valid! Username provided " + username);
        }
        User user = userDAO.findUserByUsername(username);
        return userDTOMapper.apply(user);
    }

    // temporary method
    public UserDTO updateUser(UserDTO userDTO) {
        User user = userDAO.findById(userDTO.getId());
        user.setUsername(userDTO.getUsername());
        user.setFirstname(userDTO.getFirstname());
        user.setSurname(userDTO.getSurname());
        user.setEmail(userDTO.getEmail());
        user.setPhoneNumber(userDTO.getPhoneNumber());
        System.out.println(user);
        userDAO.save(user);
        return convertToDto(user);
    }

    public void deleteUser(int id) {
        userDAO.deleteById(id);
    }

    public UserDTO convertToDto(User user) {
        return modelMapper.map(user, UserDTO.class);
    }

    public User convertToEntity(UserDTO userDTO) {
        return modelMapper.map(userDTO, User.class);
    }
}