package com.BillingSoftware.customerService;

import com.BillingSoftware.customerEntity.CustomerEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.BillingSoftware.customerRepository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public List<CustomerEntity> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Optional<CustomerEntity> getCustomerById(Long id) {
        return customerRepository.findById(id);
    }

    public CustomerEntity saveCustomer(CustomerEntity customer) {
        return customerRepository.save(customer);
    }

    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }


public Page<CustomerEntity> getPaginatedCustomers(Pageable pageable) {
    return customerRepository.findAll(pageable);
}
}
