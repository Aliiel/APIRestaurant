package com.insy2s.APIRest_Restaurant.Models.Mappers;

import com.insy2s.APIRest_Restaurant.Models.DTO.CustomerDTO;
import com.insy2s.APIRest_Restaurant.Models.Entities.Customer;

public class CustomerMapper {

    public static CustomerDTO toCustomerDTO(Customer customer) {

        if (customer == null) {
            return null;
        }

        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setName(customer.getName());
        customerDTO.setEmail(customer.getEmail());

        return customerDTO;
    }


    public static Customer toCustomer(CustomerDTO customerDTO) {

        if (customerDTO == null) {
            return null;
        }

        Customer customer = new Customer();
        customer.setName(customerDTO.getName());
        customer.setEmail(customerDTO.getEmail());
        return customer;
    }
}
