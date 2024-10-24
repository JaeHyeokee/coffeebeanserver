package com.lec.spring.repository;

import com.lec.spring.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Test
    void registerTest() {
        User user1 = User.builder()
                .userName("admin")
                .nickName("Iam관리자")
                .password(passwordEncoder.encode("1234"))
                .email("admin@gmail.com")
                .regDate(LocalDateTime.now())
                .reliability(500)
                .role("ROLE_USER,ROLE_ADMIN")
                .build();

//        User user2 = User.builder()
//                .userName("user2")
//                .nickName("2")
//                .password(passwordEncoder.encode("1234"))
//                .email("2.@mail.com")
//                .regDate(LocalDateTime.now())
//                .reliability(500)
//                .role("ROLE_USER")
//                .build();

//        User user2 = User.builder()
//                .userName("user2".toUpperCase())
//                .password(passwordEncoder.encode("1234"))
//                .nickName("2")
//                .email("2.@mail.com")
//                .reliability(500)
//                .build();
//
//
//        User admin1 = User.builder()
//                .userName("admin1".toUpperCase())
//                .password(passwordEncoder.encode("1234"))
//                .role("ROLE_USER,ROLE_ADMIN")
//                .nickName("3")
//                .email("3.@mail.com")
//                .reliability(500)
//                .build();

        userRepository.saveAllAndFlush(List.of(user1));
    }

    @Test
    void registerTest2() {
        List<User> users = new ArrayList<>();

        for (int i = 1; i < 51; i++) {
            User user = User.builder()
                    .userName("user" + i)
                    .nickName("유저" + i)
                    .password(passwordEncoder.encode("1234"))
                    .email("user" + i + "@gmail.com")
                    .regDate(LocalDateTime.now())
                    .reliability(500)
                    .role("ROLE_USER")
                    .memberStatus(1)  // 회원탈퇴 테스트 전용
                    .build();
            users.add(user);
        }

        userRepository.saveAllAndFlush(users);
    }
}