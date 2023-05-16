package com.kobertech.kobertechv1.services.utils;

import java.time.Instant;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kobertech.kobertechv1.entities.UserEntity;
import com.kobertech.kobertechv1.repository.UserRepository;
import com.kobertech.kobertechv1.services.UserService;

@Service
public class UserServiceImplementation implements UserService {

    @Autowired
    private final UserRepository userRepository;

    public UserServiceImplementation(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<UserEntity> getUser() {
        // TODO Auto-generated method stub
        return Optional.empty();
    }

    @Override
    public UserEntity signUpUser(UserEntity user) {
        
        user.setSubmissionTimestamp(Instant.now());

        this.userRepository.save(user);

        return user;
    }

    @Override
    public UserEntity updateUser(UserEntity user) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void deleteUser(Long userId) {
        // TODO Auto-generated method stub      
    }

    
}
