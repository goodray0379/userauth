package com.devmin.userauth.domain;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor //모든 Property를 매개변수로 가지는 생성자
@NoArgsConstructor
public class UserVO {
    
    @Id
    private Long id;

    private String username;
    
    private String password;
    
}