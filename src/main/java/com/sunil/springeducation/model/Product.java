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

    @Column(length = 40)
    private String category;

    @Builder
    public Product(String name, String description, int listPrice, int price, String category) {
        this.name = name;
        this.description = description;
        this.listPrice = listPrice;
        this.price = price;
        this.category = category;
    }

    @Override
    public String toString() {
        return String.format(
                "Product[productId=%d, name='%s', description='%s', listPrice=%d, price=%d, category='%s']",
                this.productId, this.name, this.description, this.listPrice, this.price, this.category
        );
    }
}
