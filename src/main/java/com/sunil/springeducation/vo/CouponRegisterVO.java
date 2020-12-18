package com.sunil.springeducation.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class CouponRegisterVO {
    Date expireAt;
    int availableDays;
    String productId;
    String category;
    int discountPrice = 0;
    int discountPercentage = 0;

    @Override
    public String toString() {
        return String.format(
                "CouponRegisterVO[expireAt='%s', availableDays=%d, productId='%s', category='%s', discountPrice=%d, discountPercentage=%d]",
                this.expireAt, this.availableDays, this.productId, this.category, this.discountPrice, this.discountPercentage
        );
    };
}
