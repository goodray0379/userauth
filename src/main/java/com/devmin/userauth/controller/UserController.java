package com.devmin.userauth.controller;

import java.net.URI;
import java.util.Map;

import com.devmin.userauth.domain.UserVO;
import com.devmin.userauth.exception.UserNotFoundException;
import com.devmin.userauth.service.UserDaoService;

import org.springframework.http.ResponseEntity;
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

    private UserDaoService service;

    public UserController(UserDaoService service){
        this.service = service;
    }

    @GetMapping(path = "/{id}")
    public UserVO retrieveUser(@PathVariable long id){
        UserVO user = service.findOne(id);
        if(user == null){
            throw new UserNotFoundException(String.format("ID[%s] not found", id));
        }
        return user;
    }

    @PostMapping
    public ResponseEntity<UserVO> createUser(@RequestBody UserVO userVo){
        UserVO savedUser = service.save(userVo);
        
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(savedUser.getId())
                    .toUri();

        return ResponseEntity.created(location).build();
    }

    @PostMapping(path = "/auth")
    public Map<String, Object> login(@RequestBody UserVO userVo){
        return null;
    }
}