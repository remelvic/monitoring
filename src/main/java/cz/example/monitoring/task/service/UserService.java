package cz.example.monitoring.task.service;

import cz.example.monitoring.task.data.User;
import cz.example.monitoring.task.entity.UserEntity;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();
    User getUserById(Long id);
    User createUser(User user);
    User updateUserById(Long id, User user);
    void deleteUserById(Long id);
    UserEntity getUserByAccessToken(String token);
}
