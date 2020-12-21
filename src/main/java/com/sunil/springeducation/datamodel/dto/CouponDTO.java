package com.sunil.springeducation.datamodel.dto;

import com.sunil.springeducation.model.Coupon;
import lombok.Setter;
import lombok.Getter;
import lombok.AllArgsConstructor;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class CouponDTO {
    int couponId;
    Date expireAt;
    int availableDays;
    int productId;
    String category;
    int discountPrice = 0;
    int discountPercentage = 0;

    public CouponDTO(Coupon coupon) {
        this.couponId = coupon.getCouponId();
        this.expireAt = coupon.getExpireAt();
        this.availableDays = coupon.getAvailableDays();
        this.productId = coupon.getProductId();
        this.category = coupon.getCategory();
        this.discountPrice = coupon.getDiscountPrice();
        this.discountPercentage = coupon.getDiscountPercentage();
    };

    @Override
    public String toString() {
        return String.format(
                "CouponDTO[couponId=%d, expireAt='%s', availableDays=%d, productId=%d, category='%s', discountPrice=%d, discountPercentage=%d]",
                this.couponId, this.expireAt, this.availableDays, this.productId, this.category, this.discountPrice, this.discountPercentage
        );
    };
}
