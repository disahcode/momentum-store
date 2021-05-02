package com.momentum.active.store.database.repository;

import com.momentum.active.store.database.CustomerDao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<CustomerDao, Integer> {
    CustomerDao findByCustomerId(int customerId);
    @Override
    CustomerDao  saveAndFlush(CustomerDao customerDao);
}
