package com.sunil.springeducation.repository;

import com.sunil.springeducation.model.IssuedCoupon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IssuedCouponRepository extends JpaRepository<IssuedCoupon, Integer> {

};
