package com.study.funding.service;

import com.study.funding.data.MemberFundingResponse;
import com.study.funding.entity.Funding;
import com.study.funding.entity.Member;
import com.study.funding.entity.Product;
import com.study.funding.exception.entity.funding.FundingNotFoundException;
import com.study.funding.repository.FundingRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FundingServiceTest {

    @InjectMocks
    private FundingService fundingService;

    @Mock
    private FundingRepository fundingRepository;

    private Funding makeFunding(Long fundingId) {
        return Funding.builder()
                .fundingId(fundingId)
                .fundingPrice(1000L)
                .product(Product.builder()
                        .productId(1L)
                        .build())
                .member(makeMember(1L))
                .build();
    }

    private Member makeMember(Long memberId) {
        return Member.builder()
                .memberId(memberId)
                .memberName("jung")
                .build();
    }

    @DisplayName("펀딩하기 - 정상")
    @Test
    void productFunding() {
        Long fundingId = 1L;
        Funding funding = makeFunding(fundingId);

        when(
                fundingRepository.save(funding)
        ).thenReturn(
                funding
        );

        when(
                fundingRepository.findById(fundingId)
        ).thenReturn(
                Optional.ofNullable(funding)
        );

        fundingService.productFunding(funding);
        Funding saveFunding = fundingService.getFunding(fundingId);
        assertThat(saveFunding.getFundingId()).isEqualTo(fundingId);

    }

    @DisplayName("펀딩 조회 - 실패")
    @Test
    void getFunding() {
        Long fundingId = 1L;

        when(
                fundingRepository.findById(fundingId)
        ).thenReturn(
                Optional.empty()
        );

        assertThrows(FundingNotFoundException.class, () -> fundingService.getFunding(fundingId));
    }

    @Test
    void getProductFundingList() {
        Long memberId = 1L;
        Long fundingId = 1L;
        Member member = makeMember(memberId);

        when(
                fundingRepository.findAllByMember(member)
        ).thenReturn(
                List.of(makeFunding(fundingId))
        );

        List<MemberFundingResponse> productFundingList = fundingService.getProductFundingList(member);
        assertThat(productFundingList.size()).isEqualTo(1);
    }
}