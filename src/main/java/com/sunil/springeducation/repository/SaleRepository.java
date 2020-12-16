package com.sunil.springeducation.repository;

import com.sunil.springeducation.model.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SaleRepository extends JpaRepository<Sale, Integer> {
    List<Sale> findByUserId(int userId);
}
