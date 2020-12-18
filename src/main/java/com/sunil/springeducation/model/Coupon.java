package com.sunil.springeducation.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Getter
@NoArgsConstructor
@Entity
public class Coupon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int couponId;

    @Column
    Date expireAt;

    @Column
    int availableDays;

    @Column
    String productId;

    @Column
    String category;

    @Column
    int discountPrice = 0;

    @Column
    int discountPercentage = 0;

    @Builder
    public Coupon(Date expireAt, int availableDays, String productId, String category, int discountPrice, int discountParentage) {
        this.expireAt = expireAt;
        this.availableDays = availableDays;
        this.productId = productId;
        this.category = category;
        this.discountPrice = discountPrice;
        this.discountPercentage = discountPercentage;
    };

    @Override
    public String toString() {
        return String.format(
                "Coupon[couponId=%d, expireAt='%s', availableDays=%d, productId=%d, category='%s', discountPrice=%d, discountPercentage=%d]",
                this.couponId, this.expireAt, this.availableDays, this.productId, this.category. this.discountPrice, this.discountPercentage
        );
    };
}
