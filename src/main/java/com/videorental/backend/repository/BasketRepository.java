package com.videorental.backend.repository;

import com.videorental.backend.model.Basket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BasketRepository extends JpaRepository<Basket, Long> {
    List<Basket> findDistinctByOrdersIs(Long id);


}
