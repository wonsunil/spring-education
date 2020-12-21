package com.sunil.springeducation.datamodel.dto;

import com.sunil.springeducation.model.IssuedCoupon;
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class IssuedCouponDTO {
    int issuedCouponId;
    int couponId;
    int userId;
    Date expiredAt;
    boolean used = false;
    
    public IssuedCouponDTO(IssuedCoupon issuedCouon) {
        this.couponId = issuedCouon.getCouponId();
        this.userId = issuedCouon.getUserId();
        this.expiredAt = issuedCouon.getExpiredAt();
    };

    @Override
    public String toString() {
        return String.format(
                "IssuedCouponDTO[issuedCouponId=%d, couponId=%d, userId=%d, expiredAt='%s', used='%s'",
                this.issuedCouponId, this.couponId, this.userId, this.expiredAt, this.used
        );
    };
};
