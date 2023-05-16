package com.kobertech.kobertechv1.services;

import java.util.Optional;

import com.kobertech.kobertechv1.entities.UserEntity;

public interface UserService {
    UserEntity signUpUser(UserEntity user);
    UserEntity updateUser(UserEntity user);
    Optional<UserEntity> getUser();
    void deleteUser(Long userId);
}
