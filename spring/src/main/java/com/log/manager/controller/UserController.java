package com.log.manager.controller;

import com.log.manager.exception.UserNotFoundException;
import com.log.manager.model.User;
import com.log.manager.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserRepository repo;

    @GetMapping("/user")
    public List<User> getAllUsers() {
        logger.info("Retrieving users");
        return repo.findAll();
    }

    @PostMapping("/user")
    public User newUser(@RequestBody User user) {
        return repo.save(user);
    }

    @GetMapping("/user/{id}")
    public User getUser(@PathVariable Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    @PutMapping("/employees/{id}")
    public User updateUser(@RequestBody User newEmployee, @PathVariable Long id) {
        return repo.findById(id)
                .map(employee -> {
                    employee.setUsername(newEmployee.getUsername());
                    employee.setEmail(newEmployee.getEmail());
                    return repo.save(employee);
                })
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    @DeleteMapping("/user/{id}")
    public void deleteUser(@PathVariable Long id) {
        repo.deleteById(id);
    }


}
