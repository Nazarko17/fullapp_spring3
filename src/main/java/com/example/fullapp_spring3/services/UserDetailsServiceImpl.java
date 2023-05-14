package com.example.fullapp_spring3.services;

import com.example.fullapp_spring3.daos.UserDAO;
import com.example.fullapp_spring3.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserDAO userDAO;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.userDAO.findUserByUsername(username);
        if(user == null) {
            throw new UsernameNotFoundException("User not found!");
        }
        return user;
    }
}
