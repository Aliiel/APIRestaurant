package com.insy2s.APIRest_Restaurant.Models.Repositories;

import com.insy2s.APIRest_Restaurant.Models.Entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICustomerRepository extends JpaRepository<Customer,Long> {

    boolean existsByEmail(String email);
}
