package com.kobertech.kobertechv1.services;

// import java.util.Optional;

import com.kobertech.kobertechv1.entities.UserEntity;

public interface UserService {
    UserEntity signUpUser(UserEntity user);
    UserEntity updateUser(UserEntity user);
    UserEntity getUser(String email, String password);
    void deleteUser(Long userId);
}
