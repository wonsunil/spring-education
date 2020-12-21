package com.sunil.springeducation.repository;

import com.sunil.springeducation.model.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CouponRepository extends JpaRepository<Coupon, Integer> {

};
