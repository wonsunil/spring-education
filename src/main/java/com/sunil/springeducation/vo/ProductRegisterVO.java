package com.sunil.springeducation.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ProductRegisterVO {
    private String name;
    private String description;
    private int listPrice;
    private int price;
    private String category;

    @Override
    public String toString() {
        return String.format(
                "ProductRegisterVO[name='%s', description='%s', listPrice='%d', price='%d', category='%s']",
                this.name, this.description, this.listPrice, this.price, this.category
        );
    };
};
