package com.zaki.demo.usertest.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException.BadRequest;

import com.zaki.demo.usertest.model.User;
import com.zaki.demo.usertest.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/users")
    public ResponseEntity<User> createUser(@RequestBody User user) throws Exception {
        log.debug("REST request to save User: {}", user);
        if(user.getUserId() != null){
            throw BadRequest.create("User id must null", HttpStatus.INTERNAL_SERVER_ERROR, "idnotnull", null, null, null);
        }
        User result = userService.save(user);
        return ResponseEntity.created(new URI("/api/user" + result.getUserId().toString())).body(user);
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
