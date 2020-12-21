package com.sunil.springeducation.datamodel.dto;

import com.sunil.springeducation.model.Product;
import com.sunil.springeducation.model.Review;
import lombok.Setter;
import lombok.Getter;
import lombok.AllArgsConstructor;

import java.util.Collection;

@Setter
@Getter
@AllArgsConstructor
public class ProductReviewDTO {
    private int productId;
    private String name;
    private String description;
    private int listPrice;
    private int price;
    private String category;
    private String imageUrl;
    private Collection<Review> review;

    public ProductReviewDTO(Product product) {
        this.productId = product.getProductId();
        this.name = product.getName();
        this.description = product.getDescription();
        this.listPrice = product.getListPrice();
        this.price = product.getPrice();
        this.imageUrl = product.getImageUrl();
        this.review = product.getReview();
    };

    @Override
    public String toString() {
        return String.format(
                "ProductDTO[productId=%d, name='%s', description='%s', listPrice=%d, price=%d, category='%s', image='%s', review='%s']",
                this.productId, this.name, this.description, this.listPrice, this.price, this.category, this.imageUrl, this.review
        );
    }
}
