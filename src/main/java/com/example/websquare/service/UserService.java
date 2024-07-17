package com.example.websquare.service;

import java.lang.reflect.Field;
import java.util.Date;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.websquare.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import jakarta.persistence.criteria.Predicate;
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

    private User convertDtoToEntity(CreateRequestDTO createInfo) {
        User user = new User(
                createInfo.getName(),
                createInfo.getBirthday(),
                createInfo.getGender(),
                createInfo.getPhone(),
                createInfo.getEmail(),
                createInfo.getAddress(),
                createInfo.getTeam(),
                true
        );
        user.setAction("Edit");
        return user;
    }

    public Map<String, String> createUser(CreateRequest request) {
        CreateRequestDTO createInfo = request.getCreateInfo();
        Map<String, String> messageResult = new HashMap<>();
        User existingPhone = userRepository.findByPhone(createInfo.getPhone());
        User existingEmail = userRepository.findByEmail(createInfo.getEmail());
        if(existingPhone != null) {
            messageResult.put("message", "Phone is already use");
        }
        else if(existingEmail != null) {
            messageResult.put("message", "Email is already use");
        }
        else {
            User user = convertDtoToEntity(createInfo);
            userRepository.save(user);
            messageResult.put("message", "User created successfully");
        }
        return messageResult;
    }

    public ResponseEntity<Map<String, List<User>>> getUsers(SearchRequest request) throws ParseException, IllegalArgumentException, IllegalAccessException {
        SearchRequestDTO searchInfo = request.getSearchInfo();
        Map<String, Object> searchParams = new HashMap<>();
        Field[] fields = searchInfo.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            Object value = field.get(searchInfo);
            if(value != null && !value.toString().isEmpty()){
                searchParams.put(field.getName(), value);
            }
        }
        Specification<User> spec = createQuery(searchParams);
        Map<String, List<User>> searchResults = new HashMap<>();
        List<User> users = userRepository.findAll(spec);
        searchResults.put("searchResults", users);
        return ResponseEntity.ok(searchResults);
    }
    
    public Specification<User> createQuery(Map<String, Object> searchParams) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            searchParams.forEach((key, value) -> {
                if (key.equals("birthdayFrom") && value != null && !value.toString().isEmpty()) {
                    predicates.add(criteriaBuilder.greaterThan(root.get("birthday").as(Date.class), (Date) value));
                } else if (key.equals("birthdayTo") && value != null) {
                    predicates.add(criteriaBuilder.lessThan(root.get("birthday").as(Date.class), (Date) value));
                } else {
                    predicates.add(criteriaBuilder.equal(root.get(key), value));
                }
            });
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }

    public void deleteUser(DeleteRequest deleteInfo) {
        List<DeleteRequestDTO> userToDelete = deleteInfo.getSearchResults();
        for(DeleteRequestDTO data : userToDelete){
            if (data.getChecked().equals("1")) {
                User user = userRepository.findByPhone(data.getPhone());
                userRepository.delete(user);
            }
        }
    }
}
