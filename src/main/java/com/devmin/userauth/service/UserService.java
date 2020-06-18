package com.devmin.userauth.service;

import java.util.List;
import java.util.Optional;

import com.devmin.userauth.domain.User;
import com.devmin.userauth.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public Optional<User> findById(long id){
        return userRepository.findById(id);
    }

    public User save(User user) {
        userRepository.save(user);
        return user;
    }

    public void deleteById(long id){
        userRepository.deleteById(id);
    }

    public User match(User reqUser){
        
        return null;
    }

}