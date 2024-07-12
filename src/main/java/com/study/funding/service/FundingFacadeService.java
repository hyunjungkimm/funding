package com.study.funding.service;

import com.study.funding.data.FundingRequest;
import com.study.funding.data.FundingResponse;
import com.study.funding.data.FundingStatus;
import com.study.funding.data.MemberFundingResponse;
import com.study.funding.entity.Funding;
import com.study.funding.entity.Member;
import com.study.funding.entity.Product;
import com.study.funding.exception.service.funding.FundingServiceException;
import com.study.funding.repository.FundingRepository;
import com.study.funding.repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.study.funding.error.ErrorCode.FUNDING_AMOUNT_IS_GREATER_THAN_CAN_BE_FUNDED_AMOUNT;
import static com.study.funding.error.ErrorCode.SOLD_OUT;

@Service
@RequiredArgsConstructor
@Slf4j
public class FundingFacadeService {

    private final ProductService productService;
    private final MemberService memberService;
    private final FundingService fundingService;

    @Transactional
    public FundingResponse productFunding(long memberId, long productId, FundingRequest fundingRequest) {

        Product product = productService.getProduct(productId);

        checkFundingStatus(product, fundingRequest);

        if(product.getFundingStatus().equals(FundingStatus.IN_PROGRESS.getStatus())) {
            productService.updateProduct(product);
            Product updateProduct = productService.getProduct(productId);

            Member member = memberService.getMember(memberId);

            Funding funding = new Funding();
            funding.setFundingPrice(fundingRequest.getFundingPrice());
            funding.setProduct(updateProduct);
            funding.setMember(member);
            fundingService.productFunding(funding);

            FundingResponse fundingResponse = new FundingResponse();
            fundingResponse.setFundingStatus("펀딩 성공");
            return fundingResponse;
        } else {
            log.info("펀딩 종료");
            throw new FundingServiceException(SOLD_OUT);
        }
    }

    private void checkFundingStatus(Product product, FundingRequest fundingRequest) {
        long currentFundingPrice = product.getTotalFundingPrice();
        long fundingPriceSum  = currentFundingPrice + fundingRequest.getFundingPrice();

        if(fundingPriceSum > product.getTargetFundingPrice()) {
            log.info("{} 원 펀딩 가능합니다.", fundingPriceSum - product.getTargetFundingPrice());
            throw new FundingServiceException(FUNDING_AMOUNT_IS_GREATER_THAN_CAN_BE_FUNDED_AMOUNT);
        }else if(fundingPriceSum == product.getTargetFundingPrice()){
            product.setFundingStatus(FundingStatus.COMPLETED.getStatus());
        }else {
            product.setFundingStatus(FundingStatus.IN_PROGRESS.getStatus());
        }
    }

    public List<MemberFundingResponse> getProductFundingList(Long memberId) {
        Member member = memberService.getMember(memberId);
        return fundingService.getProductFundingList(member);
    }
}
