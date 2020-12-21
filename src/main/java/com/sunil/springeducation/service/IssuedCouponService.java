package com.sunil.springeducation.service;

import com.sunil.springeducation.datamodel.dto.IssuedCouponDTO;
import com.sunil.springeducation.model.Coupon;
import com.sunil.springeducation.model.IssuedCoupon;
import com.sunil.springeducation.repository.CouponRepository;
import com.sunil.springeducation.repository.IssuedCouponRepository;
import com.sunil.springeducation.util.DateUtil;
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

    public IssuedCouponDTO issueCouponById(int issueCouponId) throws Exception {
        return new IssuedCouponDTO(this.issuedCouponRepository.findById(issueCouponId).orElseThrow((() -> new Exception("해당 ID로 발급된 쿠폰을 찾지 못했습니다."))));
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
