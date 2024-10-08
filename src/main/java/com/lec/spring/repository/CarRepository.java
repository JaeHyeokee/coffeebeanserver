package com.lec.spring.repository;

import com.lec.spring.domain.Car;
import com.lec.spring.domain.enums.DealingStatus;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CarRepository extends JpaRepository<Car, Long> {

    List<Car> findCarByCategory2AndDealingStatus(String category2, DealingStatus dealingStatus);

    List<Car> findCarByCategory1OrderByRegDateDesc(String category1);

    List<Car> findCarByCategory1AndCategory2OrderByRegDateDesc(String category1, String category2);

    List<Car> findByUser_userId(Long userId, Sort sort);

    List<Car> findTop10ByDealingStatusOrderByRegDateDesc(DealingStatus dealingStatus);

    @Query("SELECT c FROM car c WHERE "
            + "(:category1 IS NULL OR c.category1 = :category1) AND "
            + "(:category2 IS NULL OR c.category2 = :category2)")
    List<Car> findCarsByCategories(
            @Param("category1") String category1,
            @Param("category2") String category2
    );
}