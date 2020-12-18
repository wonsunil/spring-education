package com.sunil.springeducation.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@NoArgsConstructor
@Entity
public class IssuedCoupon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int issuedCouponId;

    @Column
    int couponId;

    @Column
    int userId;

    @Column
    Date expiredAt;

    @Column
    @Setter
    boolean used = false;

    @Builder
    public IssuedCoupon(int couponId, int userId, Date expiredAt) {
        this.couponId = couponId;
        this.userId = userId;
        this.expiredAt = expiredAt;
    };

    @Override
    public String toString() {
        return String.format(
                "IssuedCoupon[issuedCouponId=%d, couponId=%d, userId=%d, expiredAt='%s', used='%s'",
                this.issuedCouponId, this.couponId, this.userId, this.expiredAt, this.used
        );
    };
};
