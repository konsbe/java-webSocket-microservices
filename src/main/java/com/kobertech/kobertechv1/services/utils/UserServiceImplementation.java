package com.kobertech.kobertechv1.services.utils;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.kobertech.kobertechv1.entities.UserEntity;
import com.kobertech.kobertechv1.exceptions.UserExceptions;
import com.kobertech.kobertechv1.repository.UserRepository;
import com.kobertech.kobertechv1.services.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImplementation implements UserService {

    @Autowired
    private final UserRepository userRepository;

    @Override
    public UserEntity getUser(String email, String password) {
        return null;
        // return userRepository.findByEmailAndPassword(email, password)
        //         .orElseThrow(() -> {
        //             UserExceptions exception = new UserExceptions(
        //                     String.format("User with email %s does not exist", email));
        //             exception.setStatusCode(HttpStatus.NOT_ACCEPTABLE.value());
        //             return exception;
        //         });
    }

    @Override
    public UserEntity signUpUser(UserEntity user) {

        user.setSubmissionTimestamp(Instant.now());

        this.userRepository.save(user);

        return user;
    }

    @Override
    public UserEntity updateUser(UserEntity user) {

        this.userRepository.save(user);

        return user;
    }

    @Override
    public void deleteUser(Long userId) {

        this.userRepository.deleteById(userId);
    }

}
