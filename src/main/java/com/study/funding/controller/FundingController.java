package com.study.funding.controller;

import com.study.funding.data.FundingRequest;
import com.study.funding.data.FundingResponse;
import com.study.funding.service.FundingServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class FundingController {

    private final FundingServiceImpl fundingService;

    @PostMapping("/products/{product_id}/funding")
    public FundingResponse productFunding(@RequestAttribute Long userId,
                                          @PathVariable(name= "product_id") Long productId,
                                          @RequestBody @Validated FundingRequest fundingRequest) {
        //TODO : @RequestBody FundingRequest 객체 데이터 타입이 다를 경우 예외처리
        return fundingService.productFunding(userId, productId, fundingRequest);
    }
}
