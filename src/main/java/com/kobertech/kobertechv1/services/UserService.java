package com.kobertech.kobertechv1.services;

import java.util.Map;
import java.util.Optional;

import com.kobertech.kobertechv1.entities.UserEntity;

public interface UserService {
    UserEntity signUpUser(UserEntity user);
    UserEntity updateUser(UserEntity user);
    Optional<UserEntity> getUser(Map<String, Object> searchCriteria);
    void deleteUser(Long userId);
}
