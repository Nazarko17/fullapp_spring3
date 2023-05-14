package com.example.fullapp_spring3.services;

import com.example.fullapp_spring3.daos.UserDAO;
import com.example.fullapp_spring3.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserDAO userDAO;

    public User saveUser(User user) throws Exception {
        return userDAO.save(user);
    }

    public User getUser(String username) {
        return userDAO.findUserByUsername(username);
    }

    public void deleteUser(int id) {
        userDAO.deleteById(id);
    }
}
