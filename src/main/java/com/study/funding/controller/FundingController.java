package com.study.funding.controller;

import com.study.funding.data.FundingRequest;
import com.study.funding.data.FundingResponse;
import com.study.funding.service.FundingServiceImpl;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Validated
public class FundingController {

    private final FundingServiceImpl fundingService;

    @PostMapping("/products/{product_id}/funding")
    @ResponseBody
    public FundingResponse productFunding(@RequestAttribute Long memberId,
                                          @PathVariable(name= "product_id") @Positive Long productId,
                                          @RequestBody @Valid FundingRequest fundingRequest) {
          return fundingService.productFunding(memberId, productId, fundingRequest);
    }
}
