package com.lec.spring.repository;

import com.lec.spring.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    Boolean existsByUserName(String userName);

    User findByUserName(String userName);

    User findByUserId(Long userId);
}