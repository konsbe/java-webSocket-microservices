package com.kobertech.kobertechv1.services;

// import java.util.Optional;

import com.kobertech.kobertechv1.entities.UserEntity;

public interface UserService {
    UserEntity updateUser(UserEntity user);
    UserEntity signUpUser(UserEntity user);
    UserEntity getUser(String email);
    void deleteUser(String userId);
}
