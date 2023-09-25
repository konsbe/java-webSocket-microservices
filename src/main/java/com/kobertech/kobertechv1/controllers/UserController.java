package com.kobertech.kobertechv1.controllers;

import com.kobertech.kobertechv1.entities.UserEntity;
import com.kobertech.kobertechv1.services.UserService;

import lombok.RequiredArgsConstructor;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/account")
@RequiredArgsConstructor
public class UserController  {

    @Autowired
    private final UserService userService;

    @PostMapping("/get-user")
    public ResponseEntity<UserEntity> getUser(@RequestBody String id) throws Exception {
        // Check if user exists
        // Perform sign-up 
        return new ResponseEntity<>(userService.getUser(id), HttpStatus.OK);
        // Access account.getUserName(), account.getEmail(), account.getPassword(), etc.
        // Save the account to the database or perform any other necessary operations
        // You can also return a response or redirect to a success page
    }

}
