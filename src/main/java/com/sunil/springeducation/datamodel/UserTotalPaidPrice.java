package com.sunil.springeducation.datamodel;

import lombok.Getter;

@Getter
public class UserTotalPaidPrice {
    int userId;
    int totalPaidPrice;

    public UserTotalPaidPrice(SaleGroupByUserId saleGroupByUserId) {
        this.userId = saleGroupByUserId.getUserId();
        this.totalPaidPrice = saleGroupByUserId.getTotalPurchaseAmount();
    };

    @Override
    public String toString() {
        return String.format(
                "UserTotalPaidPrice[userId=%d, totalPaidPrice=%d]",
                this.userId, this.totalPaidPrice
        );
    };
}
