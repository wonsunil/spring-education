package com.sunil.springeducation.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int productId;

    @Column(length = 80, nullable = false)
    private String name;

    @Column
    private String description;

    @Column
    private int listPrice;

    @Column
    private int price;


    @Builder
    public Product(String name, String description, int listPrice, int price) {
        this.name = name;
        this.description = description;
        this.listPrice = listPrice;
        this.price = price;
    }

    @Override
    public String toString() {
        return String.format(
                "Product[productId=%d, name='%s', description='%s', listPrice=%d, price=%d]",
                this.productId, this.name, this.description, this.listPrice, this.price
        );
    }
}
