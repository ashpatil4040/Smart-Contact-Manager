package com.scm.services;

import java.util.List;
import java.util.Optional;

import com.scm.entities.User;
import com.scm.forms.UserFrom;

public interface UserService {

    User saveUser(User user);
    Optional<User> getUserById(String id);
    Optional<User> updateUser(User user);
    void deleteUser(String id);
    boolean isUserExistByEmail(String email);
    boolean isUserExist(String userId);
    List<User> getAllUsers();
    User getUserByEmail(String email);
}
