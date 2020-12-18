package com.sunil.springeducation.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class IssueCouponVo {
    int couponId;
    int userId;

    @Override
    public String toString() {
        return String.format(
                "IssuedCouponVO[couponId=%d, userId=%d]",
                this.couponId, this.userId
        );
    };
};
