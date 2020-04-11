package io.qkits.benchmarks.db.jpa.repository;

import io.qkits.benchmarks.db.jpa.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepo extends JpaRepository<Customer, Integer> {
}

