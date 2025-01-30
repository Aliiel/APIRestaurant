package com.insy2s.APIRest_Restaurant.Models.Repositories;

import com.insy2s.APIRest_Restaurant.Models.Entities.Dish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IDishRepository extends JpaRepository<Dish, Long> {

    boolean existsByName(String name);
    Optional<Dish> findByName(String name);

}
