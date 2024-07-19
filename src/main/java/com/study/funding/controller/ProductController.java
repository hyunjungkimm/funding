package com.study.funding.controller;

import com.study.funding.data.ProductResponse;
import com.study.funding.entity.Product;
import com.study.funding.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/api/v1/products")
    public List<Product> getAllProduct() {
        return productService.getProductList();
    }

    @GetMapping("/api/v2/products")
    public List<ProductResponse> getAllProducts() {
        return productService.getProducts();
    }

    @GetMapping("/api/v3/products")
    public Page<ProductResponse> getAllProductWithPAgeByQueryMethod(@RequestParam int size, @RequestParam int page) {
        return productService.getProductProgress(size, page);
    }

    @GetMapping("/api/v4/products")
    public Slice<ProductResponse> getAllProduct(Pageable pageable) {
        return productService.getAllProducts(pageable);
    }

}
