package com.example.websquare.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.example.websquare.model.User;
import com.example.websquare.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    
    @GetMapping("/getUser")
    public ResponseEntity<User> getUser(@RequestParam String name) {
        return userService.getUser(name);
    }

    @GetMapping("path")
    public List<User> getUsers(@RequestParam Map<String,String> allParams) throws ParseException{
        return userService.getUsers(allParams);
    }

    @PostMapping("/createUser")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        return userService.createUser(user);
    }
    
    @DeleteMapping("/deleteUser")
    public ResponseEntity<String> deleteUser(@RequestBody User user) {
        return userService.deleteUser(user);
    }

    
    
    
}
