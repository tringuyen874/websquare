package com.example.websquare.controller;

import com.example.websquare.model.CreateRequest;
import com.example.websquare.model.DeleteRequest;
import com.example.websquare.model.DeleteRequestDTO;
import com.example.websquare.model.SearchRequest;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import com.example.websquare.model.User;
import com.example.websquare.service.UserService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    
    @GetMapping("/getUser")
    public ResponseEntity<User> getUser(@RequestParam String name) {
        return userService.getUser(name);
    }

    @PostMapping("/getUsers")
    public ResponseEntity<Map<String, List<User>>> getUsers(@RequestBody SearchRequest searchRequest) throws ParseException, IllegalArgumentException, IllegalAccessException{
        return userService.getUsers(searchRequest);
    }

    @PostMapping("/createUser")
    public Map<String, Map<String, String>> createUser(@RequestBody CreateRequest createRequest) {
        Map<String, Map<String, String>> createResult = new HashMap<>();
        createResult.put("messageError", userService.createUser(createRequest));
        return createResult;
    }
    
    @DeleteMapping("/deleteUsers")
    public void deleteUser(@RequestBody DeleteRequest deleteInfo) {
        userService.deleteUser(deleteInfo);
    }
}
