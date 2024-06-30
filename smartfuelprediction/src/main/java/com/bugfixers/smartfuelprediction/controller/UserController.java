package com.bugfixers.smartfuelprediction.controller;

import com.bugfixers.smartfuelprediction.entity.User;
import com.bugfixers.smartfuelprediction.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/createOrUpdate")
    public ResponseEntity<Map<String, Object>> createOrUpdateUser(@RequestBody User user, BindingResult bindingResult) {
        Map<String, Object> response = new HashMap<>();

        if (bindingResult.hasErrors()) {
            response.put("errors", bindingResult.getAllErrors());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        User savedUser = userService.createUser(user);
        response.put("user", savedUser);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> loginUser(@RequestBody Map<String, String> credentials) {
        Map<String, Object> response = new HashMap<>();

        String email = credentials.get("email");
        String password = credentials.get("password");

        if (email == null || password == null) {
            response.put("error", "Email and password are required");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        User user = userService.getUserByEmail(email);

        if (user != null && user.getPassword().equals(password)) {
            response.put("user", user);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.put("error", "Invalid email or password");
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        }
    }
}
