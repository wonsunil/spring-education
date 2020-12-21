package com.sunil.springeducation.route;

import com.sunil.springeducation.datamodel.dto.ProductDTO;
import com.sunil.springeducation.service.ProductService;
import com.sunil.springeducation.model.Product;
import com.sunil.springeducation.datamodel.vo.ProductRegisterVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductRoute {
    private final ProductService productService;

    @Autowired
    public ProductRoute(ProductService productService) {
        this.productService = productService;
    };

    @GetMapping("")
    @ResponseBody
    public List<ProductDTO> getProducts() {
        return this.productService.products();
    };

    @GetMapping("/{productId}")
    @ResponseBody
    public ProductDTO getProduct(@PathVariable(value = "productId") String productId) throws Exception{
        return this.productService.productById(Integer.parseInt(productId));
    };

    @GetMapping("/initialize")
    public void initializers() {
        this.productService.initializeProducts();
    };

    @PostMapping("")
    public int createProduct(ProductRegisterVO product) {
        return this.productService.createProduct(product);
    };

    @DeleteMapping("/{productId}")
    public void deleteProduct(@PathVariable(value = "productId") String productId) {
        this.productService.deleteProduct(Integer.parseInt(productId));
    };
    
    @GetMapping("/category/{categoryName}")
    @ResponseBody
    public List<ProductDTO> getProductsByCategory(@PathVariable(value = "categoryName") String categoryName) {
        return this.productService.productsByCategory(categoryName);
    };
};
