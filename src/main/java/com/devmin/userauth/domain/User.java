package com.devmin.userauth.domain;

import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="user")
@JsonIgnoreProperties(value={"password"})
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column
    @Size(min=2, message = "Name은 2글자 이상 입력해 주세요.")
    private String username;
    
    @Column
    private String password;

    @Column
    private String name;

    @Past
    @CreationTimestamp
    private Date joinDate;
    
}