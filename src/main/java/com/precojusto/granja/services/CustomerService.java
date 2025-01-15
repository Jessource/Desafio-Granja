package com.precojusto.granja.services;
import com.precojusto.granja.model.Customer;
import com.precojusto.granja.model.Sale;
import com.precojusto.granja.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    public Page<Customer> listAllCustomers(Pageable pageable) {
        return customerRepository.findAll(pageable);
    }

}