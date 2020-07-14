package com.devmin.userauth.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import com.devmin.userauth.component.JwtTokenProvider;
import com.devmin.userauth.domain.User;
import com.devmin.userauth.exception.UserNotFoundException;
import com.devmin.userauth.service.UserService;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserService service;


    @GetMapping
    public List<User> retrieveAllUsers(){
        return service.findAll();
    }

    @PostMapping
    public ResponseEntity<User> createUser(@Valid @RequestBody User user){

        user.setPassword( passwordEncoder.encode( user.getPassword() ) );
        service.save(user);
        
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(user.getId())
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

    @PostMapping(path = "/token")
    public String login(@RequestBody User user){
        User member = service.findByUsername( user.getUsername() ).orElseThrow( () -> new UserNotFoundException( String.format( "Username[%s] not found", user.getUsername()) ) );
        if(passwordEncoder.matches(user.getPassword(), member.getPassword())){
            List<String> auth = new ArrayList<String>();
            auth.add(member.getAuthority());
            return jwtTokenProvider.createToken( member.getUsername(), auth );
        }else{
           throw new UserNotFoundException( String.format( "Username[%s] not found", user.getUsername() ) );
        }
    }
}