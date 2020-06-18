package com.devmin.userauth.controller;

import java.net.URI;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import com.devmin.userauth.domain.User;
import com.devmin.userauth.exception.UserNotFoundException;
import com.devmin.userauth.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService service;


    @GetMapping
    public List<User> retrieveAllUsers(){
        return service.findAll();
    }

    @PostMapping
    public ResponseEntity<User> createUser(@Valid @RequestBody User user){
        User savedUser = service.save(user);
        
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(savedUser.getId())
                    .toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping(path = "/{id}")
    public User retrieveUser(@PathVariable long id){
        return service.findById(id).orElseThrow( ()-> new UserNotFoundException(String.format("ID[%s] not found", id)) );
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable int id){
        service.deleteById(id);
    }

    @PostMapping(path = "/auth")
    public Map<String, Object> login(@RequestBody User user){
        return null;
    }
}