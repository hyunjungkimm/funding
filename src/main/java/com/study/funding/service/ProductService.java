package com.study.funding.service;

import com.study.funding.data.FundingStatus;
import com.study.funding.data.ProductFundingResponse;
import com.study.funding.data.ProductResponse;
import com.study.funding.entity.Funding;
import com.study.funding.entity.Product;
import com.study.funding.exception.entity.prodcut.ProductNotFoundException;
import com.study.funding.repository.ProductRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static com.study.funding.error.ErrorCode.NOT_FOUND_PRODUCT;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    @Transactional(readOnly = true)
    public Product getProduct(long productId) {
        return productRepository.findById(productId).orElseThrow(() -> new ProductNotFoundException(NOT_FOUND_PRODUCT));
    }

    @Transactional
    public void updateProduct(Product product) {
        Product updateProduct =  productRepository.findById(product.getProductId()).orElseThrow(()
                -> new ProductNotFoundException(NOT_FOUND_PRODUCT));

        updateProduct.setProductId(product.getProductId());
        updateProduct.setProductName(product.getProductName());
        updateProduct.setTargetFundingPrice(product.getTargetFundingPrice());
        updateProduct.setTotalFundingPrice(product.getTotalFundingPrice());
        updateProduct.setFundingStatus(product.getFundingStatus());
        updateProduct.setStartDate(product.getStartDate());
        updateProduct.setFinishDate(product.getFinishDate());
        updateProduct.setFundingMemberNumber(product.getFundingMemberNumber() + 1);
    }

    @Transactional(readOnly = true)
    public List<Product> getProductList() {
        return productRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<ProductResponse> getProducts() {
        List<Product> productList = productRepository.findAll();
        List<ProductResponse> productResponseList = new ArrayList<>();
        for (Product product : productList) {
            ProductResponse productResponse = new ProductResponse();
            productResponse.setProductId(product.getProductId());
            productResponse.setProductName(product.getProductName());
            productResponse.setTargetFundingPrice(product.getTargetFundingPrice());
            productResponse.setTotalFundingPrice(product.getTotalFundingPrice());
            productResponse.setFundingMemberNumber(product.getFundingMemberNumber());
            productResponse.setStartDate(product.getStartDate());
            productResponse.setFinishDate(product.getFinishDate());
            List<Funding> fundingList = product.getFundingList();
            List<ProductFundingResponse> productFundingResponseList = new ArrayList<>();
            for (Funding funding : fundingList) {
                ProductFundingResponse productFundingResponse = new ProductFundingResponse();
                productFundingResponse.setFundingId(funding.getFundingId());
                productFundingResponseList.add(productFundingResponse);
            }
            productResponse.setFundingList(productFundingResponseList);
            productResponseList.add(productResponse);
        }

        return productResponseList;
    }

    @Transactional(readOnly = true)
    public Page<ProductResponse> getProductProgress(int size, int page) {
        PageRequest pageRequest = PageRequest.of(size, page);
        LocalDate now = LocalDate.now();
        Page<Product> products = productRepository.findProductByFundingStatusAndStartDateLessThanEqualAndFinishDateGreaterThanEqual(
                FundingStatus.IN_PROGRESS.getStatus(), now, now, pageRequest);

        return products.map(product ->
                ProductResponse.builder()
                        .productId(product.getProductId())
                        .productName(product.getProductName())
                        .targetFundingPrice(product.getTargetFundingPrice())
                        .totalFundingPrice(product.getTotalFundingPrice())
                        .fundingMemberNumber(product.getFundingMemberNumber())
                        .startDate(product.getStartDate())
                        .finishDate(product.getFinishDate())
                        .build());
    }

    @Transactional(readOnly = true)
    public Slice<ProductResponse> getAllProducts(Pageable pageable) {
        Slice<Product> products = productRepository.findSliceByFundingStatus(FundingStatus.IN_PROGRESS.getStatus(), pageable);
        return products.map(product ->
                ProductResponse.builder()
                        .productId(product.getProductId())
                        .productName(product.getProductName())
                        .targetFundingPrice(product.getTargetFundingPrice())
                        .totalFundingPrice(product.getTotalFundingPrice())
                        .fundingMemberNumber(product.getFundingMemberNumber())
                        .startDate(product.getStartDate())
                        .finishDate(product.getFinishDate())
                        .build());
    }
}
