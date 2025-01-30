package com.insy2s.APIRest_Restaurant.Models.Repositories;

import com.insy2s.APIRest_Restaurant.Models.Entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IOrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByCustomerId(Long customerId);


}
