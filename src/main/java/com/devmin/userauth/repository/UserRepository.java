package com.devmin.userauth.repository;

import com.devmin.userauth.domain.UserVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserVO, Long>{
}