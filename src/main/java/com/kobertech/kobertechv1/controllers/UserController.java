package com.kobertech.kobertechv1.controllers;

import com.kobertech.kobertechv1.entities.UserEntity;
import com.kobertech.kobertechv1.exceptions.UserExceptions;
import com.kobertech.kobertechv1.response.ErrorResponse;
import com.kobertech.kobertechv1.services.UserService;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/account")
public class UserController  {

    @Autowired
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    

    
    @PostMapping("/signup")
    public ResponseEntity<UserEntity> signUp(@RequestBody UserEntity userEntity) throws Exception {
        // Check if user exists
        // Perform sign-up 
        return new ResponseEntity<>(userService.signUpUser(userEntity), HttpStatus.OK);
        // Access account.getUserName(), account.getEmail(), account.getPassword(), etc.
        // Save the account to the database or perform any other necessary operations
        // You can also return a response or redirect to a success page
    }

    @GetMapping("/login")
    public ResponseEntity<Object> logIn(@RequestBody UserEntity userEntity) {
    try {
        UserEntity user = userService.getUser(userEntity.getEmail(), userEntity.getPassword());
        return ResponseEntity.ok(user);
    } catch (UserExceptions exception) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setTimestamp(LocalDateTime.now());
        errorResponse.setStatus(exception.getStatusCode());
        errorResponse.setError(HttpStatus.valueOf(exception.getStatusCode()).getReasonPhrase());
        errorResponse.setMessage(String.format("user with the email %s does not exist", userEntity.getEmail()));
        errorResponse.setPath("/account/login");
        
        return ResponseEntity.status(exception.getStatusCode()).body(errorResponse);
    }
}    
}
