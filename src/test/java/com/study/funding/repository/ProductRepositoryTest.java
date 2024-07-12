package com.study.funding.repository;

import com.study.funding.data.FundingStatus;
import com.study.funding.entity.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    void findProductByFundingStatusAndStartDateLessThanEqualAndFinishDateGreaterThanEqual() {
        Page<Product> productPage = productRepository.findProductByFundingStatusAndStartDateLessThanEqualAndFinishDateGreaterThanEqual(
                FundingStatus.IN_PROGRESS.getStatus(), LocalDate.now(), LocalDate.now(), PageRequest.of(0, 5)
        );

        assertThat(productPage.getTotalPages()).isEqualTo(1L);
        assertThat(productPage.getTotalElements()).isEqualTo(2L);
    }

    @Test
    void findSliceByFundingStatus() {
        Slice<Product> sliceByFundingStatus = productRepository.findSliceByFundingStatus(FundingStatus.IN_PROGRESS.getStatus(), PageRequest.of(0, 1));

        assertThat(sliceByFundingStatus.isFirst()).isTrue();
        assertThat(sliceByFundingStatus.isLast()).isFalse();

    }
}