package com.sunil.springeducation.service;

import com.sunil.springeducation.model.Product;
import com.sunil.springeducation.repository.ProductRepository;
import com.sunil.springeducation.vo.ProductRegisterVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

@Controller
public class ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    };

    public Product find(int productId) throws Exception {
        Optional<Product> searchedProduct = this.productRepository.findById(productId);

        return searchedProduct.orElseThrow(
                () -> new Exception("해당 상품을 찾을 수 없습니다")
        );
    };

    public List<Product> findAll() {
        return this.productRepository.findAll();
    };

    public void initializeProducts() {
        Product product1 = Product.builder()
                .name("a")
                .description("a 상품입니다")
                .listPrice(15000)
                .price(12000)
                .build();

        Product product2 = Product.builder()
                .name("b")
                .description("b 상품입니다")
                .listPrice(30000)
                .price(25000)
                .build();

        Product product3 = Product.builder()
                .name("c")
                .description("c 상품입니다")
                .listPrice(100000)
                .price(75000)
                .build();

        this.productRepository.save(product1);
        this.productRepository.save(product2);
        this.productRepository.save(product3);
        this.productRepository.flush();
    };

    public int createProduct(ProductRegisterVO product) {
        Product createProduct = Product.builder()
                .name(product.getName())
                .description(product.getDescription())
                .listPrice(product.getListPrice())
                .price(product.getPrice())
                .build();

        this.productRepository.save(createProduct);
        this.productRepository.flush();

        return createProduct.getProductId();
    };

    public void deleteProduct(int productId) {
        this.productRepository.deleteById(productId);
    };
};
