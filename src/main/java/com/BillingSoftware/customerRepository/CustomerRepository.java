package com.BillingSoftware.customerRepository;

import com.BillingSoftware.customerEntity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {
}
