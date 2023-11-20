package com.learning.demo.repository;

import com.learning.demo.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Product,Integer> {
}
