package com.study.funding.repository;

import com.study.funding.entity.Funding;
import com.study.funding.entity.Member;
import com.study.funding.entity.Product;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
class FundingRepositoryTest {

    @Autowired
    private FundingRepository fundingRepository;


    private Member makeMember(Long memberId) {
        return Member.builder()
                .memberId(memberId)
                .memberName("jung")
                .build();
    }

    private Product makeProduct(Long productId) {
        return Product.builder()
                .productId(productId)
                .productName("제품명")
                .totalFundingPrice(1000L)
                .targetFundingPrice(10000L)
                .fundingMemberNumber(0)
                .build();
    }

    private Funding makeFunding(Long fundingId) {
        return Funding.builder()
                .fundingId(fundingId)
                .fundingPrice(1000L)
                .member(makeMember(1L))
                .product(makeProduct(1L))
                .build();
    }

    @DisplayName("펀딩 저장 후 총 개수 조회")
    @Test
    void findAllByMember() {
        Long memberId = 1L;
        Long fundingId = 1L;
        fundingRepository.save(makeFunding(fundingId));
        assertThat(fundingRepository.findAllByMember(makeMember(memberId)))
                .isNotNull()
                .hasSizeGreaterThanOrEqualTo(1);
    }
}