package com.sunil.springeducation.datamodel.dto;

import com.sunil.springeducation.model.Sale;
import com.sunil.springeducation.datamodel.enumModel.SaleStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;

@Getter
@Setter
@AllArgsConstructor
public class SaleDTO {
    private int saleId;
    private int userId;
    private int productId;
    private int paidPrice;
    private int listPrice;
    private int amount;
    private SaleStatus status = SaleStatus.NON_PAID;

    public SaleDTO(Sale sale) {
        this.userId = sale.getUserId();
        this.productId = sale.getProductId();
        this.paidPrice = sale.getPaidPrice();
        this.listPrice = sale.getListPrice();
        this.amount = sale.getAmount();
    }

    @Override
    public String toString() {
        return String.format(
                "SaleDTO[saleId=%d, userId=%d, productId=%d, paidPrice=%d, listPrice=%d, amount=%d, status=%d]",
                this.saleId, this.userId, this.productId, this.paidPrice, this.listPrice, this.amount, this.status
        );
    }
}
