package com.sunil.springeducation.repository;

import com.sunil.springeducation.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {

}