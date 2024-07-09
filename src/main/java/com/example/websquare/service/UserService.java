package com.example.websquare.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import jakarta.persistence.criteria.Predicate;
import com.example.websquare.model.User;
import com.example.websquare.repo.UserRepo;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepository;

    public ResponseEntity<User> getUser(String name) {
        if (name != null) {
            return ResponseEntity.ok(userRepository.findByName(name));
        }
        return ResponseEntity.status(404).build();
    }

    public ResponseEntity<User> createUser(User user) {
        if(user != null)
            return ResponseEntity.ok(userRepository.save(user));
        else
            return ResponseEntity.status(404).build();

    }

    public List<User> getUsers(Map<String, String> allParams) throws ParseException {
        Map<String, Object> convertedParams = new HashMap<>();
        for (Map.Entry<String, String> entry : allParams.entrySet()) {
            if(!entry.getValue().isEmpty()){
                switch (entry.getKey()) {
                    case "phone":
                        convertedParams.put(entry.getKey(), Integer.parseInt(entry.getValue()));
                        break;
                    case "birthFrom":
                        convertedParams.put(entry.getKey(), new SimpleDateFormat("yyyy-MM-dd").parse(entry.getValue()));
                        break;
                    case "birthTo":
                        convertedParams.put(entry.getKey(), new SimpleDateFormat("yyyy-MM-dd").parse(entry.getValue()));
                        break;
                    default:
                        convertedParams.put(entry.getKey(), entry.getValue());
                        break;
                }
            }
        }
        Specification<User> spec = withDynamicQuery(convertedParams);
        return userRepository.findAll(spec);
    }
    
    public Specification<User> withDynamicQuery(Map<String, Object> paramMaps) {
        return (root, query, cb) -> { // criteriaBuilder
            List<Predicate> predicates = new ArrayList<>();
            for (Map.Entry<String, Object> entry : paramMaps.entrySet()) {
                if(entry.getKey() != null && entry.getValue() != null){
                    predicates.add(cb.equal(root.get(entry.getKey()), entry.getValue()));
                }
            }
            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }

    public ResponseEntity<String> deleteUser(User user) {
        if(user != null){
            userRepository.delete(user);
            return ResponseEntity.ok("User deleted successfully");
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    
}
