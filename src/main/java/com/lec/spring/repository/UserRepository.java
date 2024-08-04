package com.lec.spring.repository;

import com.lec.spring.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    // 특정 username이 데이터 베이스에 존재 하는가
    Boolean existsByUserName(String userName);

    //username을 받아 DB 테이블에서 회원을 조회하는 메소드 작성
    User findByUserName(String userName);
}