package com.devmin.userauth.service;

import java.util.ArrayList;
import java.util.List;

import com.devmin.userauth.domain.UserVO;

import org.springframework.stereotype.Service;

@Service
public class UserDaoService {
    private static List<UserVO> users = new ArrayList<>();

    private static long usersCount = 4;

    static{
        users.add(new UserVO(1L, "example1", "123123"));
        users.add(new UserVO(2L, "example2", "123123"));
        users.add(new UserVO(3L, "example3", "123123"));
    }

    public List<UserVO> findAll() {
        return users;
    }

    public UserVO match(UserVO reqUser){
        for(UserVO user: users) {
            if(user.getUsername().equals(reqUser.getUsername())){
                return user;
            }
        }
        return null;
    }

    public UserVO findOne(long id){
        for(UserVO user : users){
            if(id==user.getId()){
                return user;
            }
        }
        return null;
    }

    public UserVO save(UserVO user) {
        if(user.getId()==null){
            user.setId(usersCount++);
        }

        users.add(user);
        return user;
    }

}