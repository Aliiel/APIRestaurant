package com.insy2s.APIRest_Restaurant.Services;

import com.insy2s.APIRest_Restaurant.Exceptions.CustomerAlreadyExistsException;
import com.insy2s.APIRest_Restaurant.Exceptions.CustomerNotFoundException;
import com.insy2s.APIRest_Restaurant.Models.DTO.CustomerDTO;
import com.insy2s.APIRest_Restaurant.Models.Entities.Customer;
import com.insy2s.APIRest_Restaurant.Models.Mappers.CustomerMapper;
import com.insy2s.APIRest_Restaurant.Models.Repositories.ICustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final ICustomerRepository customerRepository;

    public List<CustomerDTO> getAllCustomers() {

        return customerRepository.findAll()
                .stream()
                .map(CustomerMapper::toCustomerDTO)
                .toList();
    }


    public CustomerDTO getCustomerById(Long id) {

        Optional<Customer> customer = customerRepository.findById(id);
        return customer
                .map(CustomerMapper::toCustomerDTO)
                .orElseThrow(() -> new CustomerNotFoundException("Il n'y a pas de client enregistré avec cet id"));
    }


    public CustomerDTO createCustomer(CustomerDTO customerDTO) {

        Customer customer = CustomerMapper.toCustomer(customerDTO);
        if (customerRepository.existsByEmail(customer.getEmail())) {
            throw new CustomerAlreadyExistsException("Un client est déjà enregistré avec cette adresse email");
        }

        return CustomerMapper.toCustomerDTO(customerRepository.save(customer));
    }


    public CustomerDTO updateCustomer(Long id, CustomerDTO customerDTO) {

        Customer customerToUpdate = CustomerMapper.toCustomer(customerDTO);

        Customer existingCustomer = customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException
                        ("Il n'y a pas de client enregistré avec cet id"));

        if (customerToUpdate.getName() != null) {
            existingCustomer.setName(customerDTO.getName());
        }

        if (customerToUpdate.getEmail() != null) {
            existingCustomer.setEmail(customerDTO.getEmail());
        }

        if (customerRepository.existsByEmail(customerToUpdate.getEmail())) {
            throw new CustomerAlreadyExistsException("Un client existe déjà avec cette adresse e-mail");
        }

        return CustomerMapper.toCustomerDTO(customerRepository.save(existingCustomer));
    }


    public void deleteCustomer(Long id) {
        Optional<Customer> customer = customerRepository.findById(id);

        customer.ifPresentOrElse(customerRepository::delete,
                () -> {
                    throw new CustomerNotFoundException("Il n'y a pas de client enregistré avec cet id");
                });
    }
}
