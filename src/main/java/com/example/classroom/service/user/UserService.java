package com.example.classroom.service.user;

import com.example.classroom.model.user.User;
import com.example.classroom.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service for retrieving existing user from database or adding newly created
 */
@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Method for retrieving user from database by name if no user found created new and save in storage
     * @param userName
     * @return
     */
    public Optional<User> getUser(String userName) {
        Optional<User> user = userRepository.findByName(userName);

        if (user.isPresent()) {
            return Optional.empty();
        }

        User newUser = new User(userName, false);
        userRepository.save(newUser);
        return Optional.of(newUser);
    }

    /**
     * Method for deleting user from database if he left classroom
     * @param name
     */
    @Transactional
    public void deleteUserByName(String name) {
        userRepository.deleteUserByName(name);
    }
}
