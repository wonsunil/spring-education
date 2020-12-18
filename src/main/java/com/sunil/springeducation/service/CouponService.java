package com.sunil.springeducation.service;

import com.sunil.springeducation.model.Coupon;
import com.sunil.springeducation.repository.CouponRepository;
import com.sunil.springeducation.vo.CouponRegisterVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.Optional;

@Controller
public class CouponService {
    private final CouponRepository couponRepository;

    @Autowired
    public CouponService(CouponRepository couponRepository) {
        this.couponRepository = couponRepository;
    };

    public int createCoupon(CouponRegisterVO coupon) throws Exception{
        if(coupon.getDiscountPercentage() != 0 && coupon.getDiscountPrice() != 0) {
            throw new Exception("할인 금액과 할인 비율이 동시에 존재할 수 없습니다!");
        };

        Coupon createdCoupon = Coupon.builder()
                .availableDays(coupon.getAvailableDays())
                .category(coupon.getCategory())
                .discountParentage(coupon.getDiscountPercentage())
                .discountPrice(coupon.getDiscountPrice())
                .expireAt(coupon.getExpireAt())
                .productId(coupon.getProductId())
                .build();

        this.couponRepository.save(createdCoupon);
        this.couponRepository.flush();

        return createdCoupon.getCouponId();
    };

    public Coupon couponById(int couponId) throws Exception{
        Optional<Coupon> coupon = this.couponRepository.findById(couponId);

        return coupon.orElseThrow(() -> new Exception("해당 쿠폰을 확인할 수 없습니다"));
    };
};
