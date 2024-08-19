package com.lec.spring.repository;

import com.lec.spring.domain.Car;
import com.lec.spring.domain.enums.DealingStatus;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarRepository extends JpaRepository<Car, Long> {

    List<Car> findCarByCategory2AndDealingStatus(String category2, DealingStatus dealingStatus);

    List<Car> findCarByCategory1(String category1);

    List<Car> findCarByCategory1AndCategory2(String category1, String category2);

    List<Car> findByUser_userId(Long userId, Sort sort);
}