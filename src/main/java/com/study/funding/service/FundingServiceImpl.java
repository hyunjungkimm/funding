package com.study.funding.service;

import com.study.funding.data.FundingRequest;
import com.study.funding.data.FundingResponse;
import com.study.funding.data.FundingStatus;
import com.study.funding.entity.Funding;
import com.study.funding.entity.Product;
import com.study.funding.repository.FundingRepository;
import com.study.funding.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class FundingServiceImpl implements FundingService {

    private final FundingRepository fundingRepository;

    private final ProductRepository productRepository;

    @Override
    public FundingResponse productFunding(long memberId, long productId, FundingRequest fundingRequest) {

        Optional<Product> product = productRepository.findById(productId);

        long currentFundingPrice = product.get().getTotalFundingPrice();
        long fundingPriceSum  = currentFundingPrice + fundingRequest.getFundingPrice();

        Product updateProduct = new Product();
        updateProduct.setProductId(product.get().getProductId());

        Funding funding = new Funding();
        funding.setMemberId(memberId);
        funding.setProductId(product.get().getProductId());
        funding.setFundingPrice(fundingRequest.getFundingPrice());

        FundingResponse fundingResponse = new FundingResponse();

        if(product.get().getFundingStatus().equals(FundingStatus.IN_PROGRESS.getStatus())) {
            if(fundingPriceSum > product.get().getTargetFundingPrice()) {
                log.info("{} 원 펀딩 가능합니다.", fundingPriceSum - product.get().getTargetFundingPrice());
                //TODO Exception 처리
            }else if(fundingPriceSum == product.get().getTargetFundingPrice()){
                updateProduct.setFundingStatus(FundingStatus.COMPLETED.getStatus());
            }else {
                updateProduct.setFundingStatus(FundingStatus.IN_PROGRESS.getStatus());
            }
            updateProduct.setProductName(product.get().getProductName());
            updateProduct.setTargetFundingPrice(product.get().getTargetFundingPrice());
            updateProduct.setTotalFundingPrice(product.get().getTotalFundingPrice());
            updateProduct.setFundingMemberId(product.get().getFundingMemberId());
            updateProduct.setStartDate(product.get().getStartDate());
            updateProduct.setFinishDate(product.get().getFinishDate());
            updateProduct.setFundingMemberNumber(product.get().getFundingMemberNumber() + 1);

            productRepository.save(updateProduct);
            fundingRepository.save(funding);

            fundingResponse.setFundingStatus("모집완료");
            return fundingResponse;
        } else {
            log.info("펀딩 종료");
            //TODO Exception 처리
        }
        return fundingResponse;
    }

}
