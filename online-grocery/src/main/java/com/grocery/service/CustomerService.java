package com.grocery.service;

import com.grocery.entity.Customer;
import com.grocery.exception.ResourceNotFoundException;
import com.grocery.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository repository;

    public Customer createCustomer(Customer customer) { return repository.save(customer); }
    public List<Customer> getAllCustomers() { return repository.findAll(); }
    public Customer getCustomerById(Long id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Customer not found"));
    }
    public Customer updateCustomer(Long id, Customer data) {
        Customer existing = getCustomerById(id);
        existing.setName(data.getName());
        existing.setEmail(data.getEmail());
        existing.setAddress(data.getAddress());
        existing.setPhone(data.getPhone());
        return repository.save(existing);
    }
    public void deleteCustomer(Long id) { repository.delete(getCustomerById(id)); }
}
