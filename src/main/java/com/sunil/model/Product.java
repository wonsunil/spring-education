package com.sunil.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;

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

    @Column
    private String imageUrl;

    @OneToMany
    @JoinColumn(name = "productId")
    private Collection<Review> review;

    @Builder
    public Product(String name, String description, int listPrice, int price, String category, String imageUrl) {
        this.name = name;
        this.description = description;
        this.listPrice = listPrice;
        this.price = price;
        this.category = category;
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString() {
        return String.format(
                "Product[productId=%d, name='%s', description='%s', listPrice=%d, price=%d, category='%s', image='%s']",
                this.productId, this.name, this.description, this.listPrice, this.price, this.category, this.imageUrl
        );
    }
}
