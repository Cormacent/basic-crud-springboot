package com.zaki.demo.usertest.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException.BadRequest;

import com.zaki.demo.usertest.model.User;
import com.zaki.demo.usertest.service.UserService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/users")
    public ResponseEntity<User> createUser(@RequestBody User user) throws Exception {
        log.debug("REST request to save User: {}", user);
        if (user.getUserId() != null) {
            throw BadRequest.create("User id must null", HttpStatus.INTERNAL_SERVER_ERROR, "idnotnull", null, null, null);
        }
        User result = userService.save(user);
        return ResponseEntity.created(new URI("/api/user" + result.getUserId().toString())).body(user);
    }

    @PatchMapping("/users/{id}")
    public ResponseEntity<User> partialUpdateUser(@PathVariable Long id, @RequestBody User user) throws Exception {
        log.debug("REST request to patch User: {}", user);
        if (id == null) {
            throw BadRequest.create("User id must not be null", HttpStatus.INTERNAL_SERVER_ERROR, "idnull", null, null, null);
        }
        Optional<User> foundUser = userService.findOne(id);
        if(foundUser.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find resource");
        }

        Optional<User> result = userService.partialUpdate(user);
        return ResponseEntity.of(result);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) throws Exception {
        log.debug("REST request to patch User: {}", user);
        if (id == null) {
            throw BadRequest.create("User id must not be null", HttpStatus.INTERNAL_SERVER_ERROR, "idnull", null, null, null);
        }
        Optional<User> foundUser = userService.findOne(id);
        if(foundUser.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find resource");
        }

        User result = userService.update(user);
        return ResponseEntity.ok().body(result);
    }



    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        log.debug("REST request to get all user");
        List<User> users = userService.findAll();
        return ResponseEntity.ok().body(users);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        log.debug("REST request to get user by id: {}", id.toString());

        Optional<User> user = userService.findOne(id);
        return ResponseEntity.of(user);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<User> deleteUserById(@PathVariable Long id) {
        log.debug("REST request to delete user by id: {}", id.toString());
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
