package com.sunil.springeducation.repository;

import com.sunil.springeducation.datamodel.SaleGroupByUserId;
import com.sunil.springeducation.model.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SaleRepository extends JpaRepository<Sale, Integer> {
    List<Sale> findByUserId(int userId);

    @Query(value =
            "SELECT USER_ID as userId, SUM(PAID_PRICE) as totalPurchaseAmount " +
            "FROM SALE WHERE USER_ID = ?1 GROUP BY user_Id",
            nativeQuery = true)
    public SaleGroupByUserId PurchaseAmountGroupByUserId(int userId);
}
