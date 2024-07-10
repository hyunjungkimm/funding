package com.study.funding.controller;

import com.study.funding.data.FundingRequest;
import com.study.funding.data.FundingResponse;
import com.study.funding.service.FundingServiceImpl;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
//@Validated
public class FundingController {

    private final FundingServiceImpl fundingService;

    @PostMapping("/products/{product_id}/funding")
    public FundingResponse productFunding(@RequestAttribute @NotNull Long memberId,
                                          @PathVariable(name= "product_id") Long productId,
                                          @RequestBody @Validated FundingRequest fundingRequest) {
        //TODO : validation 예외 처리 필요
        return fundingService.productFunding(memberId, productId, fundingRequest);
    }
}
