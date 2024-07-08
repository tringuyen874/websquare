package com.example.websquare.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import jakarta.persistence.criteria.Predicate;
import com.example.websquare.model.User;
import com.example.websquare.repo.UserRepo;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepository;
    public User getUser(String name) {
        throw new UnsupportedOperationException("Unimplemented method 'getUser'");
    }

    public String createUser(User user) {
        throw new UnsupportedOperationException("Unimplemented method 'createUser'");
    }

    public List<User> getUsers(Map<String, Object> paramMaps) {
        Specification<User> spec = withDynamicQuery(paramMaps);
        return userRepository.findAll(spec);
    }
    
    public Specification<User> withDynamicQuery(Map<String, Object> paramMaps) {
        return (root, query, cb) -> { // criteriaBuilder
            List<Predicate> predicates = new ArrayList<>();
            for (Map.Entry<String, Object> entry : paramMaps.entrySet()) {
                if(entry.getKey() != null && entry.getValue() != null){
                    predicates.add(cb.equal(root.get(entry.getKey()), entry.getValue()));
                }
                // predicates.add(cb.equal(root.get(entry.getKey()), entry.getValue()));
            }
            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
