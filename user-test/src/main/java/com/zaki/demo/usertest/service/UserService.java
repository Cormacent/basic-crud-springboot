package com.zaki.demo.usertest.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zaki.demo.usertest.model.User;
import com.zaki.demo.usertest.repository.UserRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
@AllArgsConstructor
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User save(User user) {
        log.debug("Request to save User : {}", user.toString());

        return userRepository.save(user);
    }

    public User update(User user) {
        log.debug("Request to update User : {}", user.toString());
        return userRepository.save(user);
    }

    public Optional<User> partialUpdate(User user) {
        log.debug("Request to partialy update User : {}", user.toString());
        return userRepository.findById(user.getUserId())
                .map(existingUser -> {
                    if(user.getNamaLengkap() !=null){
                        existingUser.setNamaLengkap(user.getNamaLengkap());
                    }
                    if(user.getPassword() !=null){
                        existingUser.setPassword(user.getPassword());
                    }
                    if(user.getStatus() !=null){
                        existingUser.setStatus(user.getStatus());
                    }
                    if(user.getUsername() !=null){
                        existingUser.setUsername(user.getUsername());
                    }

                    return existingUser;
                })
                .map(userRepository::save);
    }

    @Transactional
    public List<User> findAll() {
        log.debug("Request to retrieve All User");
        return userRepository.findAll();
    }

    @Transactional
    public Optional<User> findOne(Long id) {
        log.debug("Request to find User by id : {}", id);

        return userRepository.findById(id);
    }

    public void delete(Long id) {
        log.debug("Request to delete User by id : {}", id);
        userRepository.deleteById(id);
    }

}
