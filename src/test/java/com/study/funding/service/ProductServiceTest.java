package com.study.funding.service;

import com.study.funding.data.FundingStatus;
import com.study.funding.data.ProductResponse;
import com.study.funding.entity.Funding;
import com.study.funding.entity.Product;
import com.study.funding.exception.entity.prodcut.ProductNotFoundException;
import com.study.funding.repository.ProductRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @InjectMocks
    private ProductService productService;

    @Mock
    private ProductRepository productRepository;

    private Product makeProduct(Long productId) {
        return Product.builder()
                .productId(productId)
                .productName("제품명")
                .totalFundingPrice(1000L)
                .targetFundingPrice(10000L)
                .fundingMemberNumber(0)
                .fundingList(List.of(makeFunding(1L)))
                .build();
    }

    private Funding makeFunding(Long fundingId) {
        return Funding.builder()
                .fundingId(fundingId)
                .fundingPrice(1000L)
                .build();
    }

    @DisplayName("제품 조회 - 정상")
    @Test
    void getProduct_success() {
        Long productId = 1L;

        when(
                productRepository.findByProductId(productId)
        ).thenReturn(
                Optional.ofNullable(makeProduct(productId))
        );

        Product product = productService.getProduct(productId);

        assertThat(product.getProductId()).isEqualTo(productId);
    }

    @DisplayName("제품 조회 - 실패")
    @Test
    void getProduct_fail() {
        Long productId = 1L;

        when(
                productRepository.findByProductId(productId)
        ).thenReturn(
                Optional.empty()
        );

        assertThrows(ProductNotFoundException.class, () -> productService.getProduct(1L));
    }

    @DisplayName("펀딩 제품 모두 조회 - 정상")
    @Test
    void getProductList() {

        Long productId = 1L;

        when(
                productRepository.findAll()
        ).thenReturn(
                List.of(makeProduct(productId))
        );

        List<Product> productList = productService.getProductList();

        assertThat(productList.get(0).getProductId()).isEqualTo(productId);
        assertThat(productList.size()).isEqualTo(1);
    }

    @DisplayName("제품 업데이트 - 정상")
    @Test
    void updateProduct() {
        Long productId = 1L;
        Product product = makeProduct(productId);

        when(
                productRepository.findByProductId(productId)
        ).thenReturn(
                Optional.ofNullable(product)
        );

        assertThat(product.getFundingMemberNumber()).isEqualTo(0);

        productService.updateProduct(product);

        assertThat(product.getFundingMemberNumber()).isEqualTo(1);
    }

    @DisplayName("펀딩 제품 모두 조회(list - entity 대신 반환 객체) - 성공")
    @Test
    void getProducts() {
        Long productId = 1L;

        when(
                productRepository.findAll()
        ).thenReturn(
                List.of(makeProduct(productId))
        );

        List<ProductResponse> products = productService.getProducts();
        assertThat(products.size()).isEqualTo(1);
        assertThat(products.get(0).getFundingList().get(0).getFundingId()).isEqualTo(1L);
    }

    @DisplayName("펀딩 제품 반환 객체로 모두 조회(page) - 성공")
    @Test
    void getProductProgress() {

        Long productId = 1L;
        int size = 0;
        int page = 5;
        LocalDate now = LocalDate.now();
        String fundingStatus = FundingStatus.IN_PROGRESS.getStatus();

        when(
                productRepository.findProductByFundingStatusAndStartDateLessThanEqualAndFinishDateGreaterThanEqual(
                        fundingStatus, now, now,  PageRequest.of(size, page)
                )
        ).thenReturn(
                 new PageImpl<>(List.of(makeProduct(productId)))
        );

        Page<ProductResponse> productProgress = productService.getProductProgress(size, page);

        assertThat(productProgress.getTotalElements()).isEqualTo(1);
        assertThat(productProgress.getTotalPages()).isEqualTo(1);
    }

    @DisplayName("펀딩 제품 반환 객체로 모두 조회(slice) - 성공")
    @Test
    void getAllProducts() {
        Long productId = 1L;
        String fundingStatus = FundingStatus.IN_PROGRESS.getStatus();
        Pageable pageable = PageRequest.of(0, 5);

        when(
                productRepository.findSliceByFundingStatus(fundingStatus, pageable)
        ).thenReturn(
                new SliceImpl<>(List.of(makeProduct(productId)))
        );

        Slice<ProductResponse> allProducts = productService.getAllProducts(pageable);

        assertThat(allProducts.isFirst()).isTrue();
        assertThat(allProducts.isLast()).isTrue();
    }
}