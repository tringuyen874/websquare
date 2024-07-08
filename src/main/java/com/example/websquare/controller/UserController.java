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
        User user = userService.getUser(name);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
    }

    @GetMapping("path")
    public List<User> getUsers(@RequestParam Map<String,String> allParams) throws ParseException{
        Map<String, Object> convertedParams = new HashMap<>();
        for (Map.Entry<String, String> entry : allParams.entrySet()) {
            switch (entry.getKey()) {
                case "phone":
                    convertedParams.put(entry.getKey(), Integer.parseInt(entry.getValue()));
                    break;
                case "birthFrom":
                case "birthTo":
                    convertedParams.put(entry.getKey(), new SimpleDateFormat("yyyy-MM-dd").parse(entry.getValue()));
                    break;
                default:
                    convertedParams.put(entry.getKey(), entry.getValue());
                    break;
            }
        }
        return userService.getUsers(convertedParams);
    }

    @PostMapping("/createUser")
    public String createUser(@RequestBody User user) {
        return userService.createUser(user);
    }
    
    @DeleteMapping("/deleteUser")
    public String deleteUser(@RequestBody String param) {
        return new String();
    }

    
    
    
}
