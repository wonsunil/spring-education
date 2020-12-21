package com.sunil.service;

import com.sunil.model.Coupon;
import com.sunil.model.IssuedCoupon;
import com.sunil.repository.CouponRepository;
import com.sunil.repository.IssuedCouponRepository;
import com.sunil.util.DateUtil;
import org.springframework.stereotype.Controller;

import java.util.Date;
import java.util.Optional;

@Controller
public class IssuedCouponService {
    private final IssuedCouponRepository issuedCouponRepository;
    private final CouponRepository couponRepository;

    public IssuedCouponService(IssuedCouponRepository issuedCouponRepository, CouponRepository couponRepository) {
        this.issuedCouponRepository = issuedCouponRepository;
        this.couponRepository = couponRepository;
    };

    public IssuedCoupon issueCouponById(int issueCouponId) throws Exception {
        return this.issuedCouponRepository.findById(issueCouponId).orElseThrow((() -> new Exception("해당 ID로 발급된 쿠폰을 찾지 못했습니다.")));
    };

    public int issueCoupon(int couponId, int userId) throws Exception{
        Optional<Coupon> SearchedCoupon = this.couponRepository.findById(couponId);
        Coupon coupon = SearchedCoupon.orElseThrow(() -> new Exception("해당 쿠폰을 찾지 못했습니다."));

        Date expireDate = null;
        Date addedDate = DateUtil.addDays(new Date(), coupon.getAvailableDays());

        int compareDate = addedDate.compareTo(coupon.getExpireAt());

        if(compareDate == 1) {
            expireDate = coupon.getExpireAt();
        }else if(compareDate == -1) {
            expireDate = addedDate;
        }else {
            expireDate = addedDate;
        };

        IssuedCoupon issuedCoupon = IssuedCoupon.builder()
                .couponId(couponId)
                .expiredAt(expireDate)
                .userId(userId)
                .build();

        this.issuedCouponRepository.save(issuedCoupon);
        this.issuedCouponRepository.flush();

        return issuedCoupon.getIssuedCouponId();
    };
};