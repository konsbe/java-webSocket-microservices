package com.kobertech.kobertechv1.controllers;

import com.kobertech.kobertechv1.entities.UserEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/account")
public class SignUpController  {

    @PostMapping("/signup")
    public void signUp(@RequestBody UserEntity account) {
        // Check if user exists
        // Perform sign-up 
        // Access account.getUserName(), account.getEmail(), account.getPassword(), etc.
        // Save the account to the database or perform any other necessary operations
        // You can also return a response or redirect to a success page
    }
}
