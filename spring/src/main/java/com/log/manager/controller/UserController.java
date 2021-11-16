package com.log.manager.controller;

import com.log.manager.exception.UserNotFoundException;
import com.log.manager.model.User;
import com.log.manager.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @PutMapping("/user/{id}")
    public User updateUser(@RequestBody User newUser, @PathVariable Long id) {
        return repo.findById(id)
                .map(user -> {
                    user.setUsername(newUser.getUsername());
                    user.setEmail(newUser.getEmail());
                    return repo.save(user);
                })
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        try{
            repo.deleteById(id);
            return new ResponseEntity<>("User with id=" + id + " deleted successfully.", HttpStatus.OK);
        }catch (EmptyResultDataAccessException e) {
            return new ResponseEntity<>("User with id=" + id + " not found.", HttpStatus.NOT_FOUND);
        }
    }


}
