package com.sunil.springeducation.model;

import com.sunil.springeducation.datamodel.SaleStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int saleId;

    @Column(nullable = false)
    private int userId;

    @Column(nullable = false)
    private int productId;

    @Column(nullable = false)
    private int paidPrice;

    @Column(nullable = false)
    private int listPrice;

    @Column(nullable = false)
    private int amount;

    @Setter
    @Enumerated(EnumType.STRING)
    private SaleStatus status = SaleStatus.NON_PAID;

    @Builder
    public Sale(int userId, int productId, int paidPrice, int listPrice, int amount, String status) {
        this.userId = userId;
        this.productId = productId;
        this.paidPrice = paidPrice;
        this.listPrice = listPrice;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return String.format(
                "Sale[saleId=%d, userId=%d, productId=%d, paidPrice=%d, listPrice=%d, amount=%d, status=%d]",
                this.saleId, this.userId, this.productId, this.paidPrice, this.listPrice, this.amount, this.status
        );
    }
}
