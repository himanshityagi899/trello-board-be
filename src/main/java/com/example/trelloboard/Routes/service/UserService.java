package com.example.trelloboard.Routes.service;




import com.example.trelloboard.Routes.repository.UserRepository;
import com.example.trelloboard.Routes.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService
{
    @Autowired
    private UserRepository userRepository;

    public User saveUser(User user) {
        // Do not encode the password
        return userRepository.save(user);
    }

    public User findByEmail(String email) {
        try {
            List<User> users = userRepository.findByEmail(email);
            if (users.isEmpty()) {
                return null;
            } else if (users.size() == 1) {
                return users.get(0);
            } else {
                // Handle the case where multiple users are returned
                throw new IllegalStateException("Multiple users found with the same email.");
            }
        } catch (DataAccessException e) {
            // Handle the data access exception
            throw new RuntimeException("Database error while fetching user by email", e);
        }
    }
}
