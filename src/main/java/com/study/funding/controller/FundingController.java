package com.study.funding.controller;

import com.study.funding.data.FundingRequest;
import com.study.funding.data.FundingResponse;
import com.study.funding.data.MemberFundingResponse;
import com.study.funding.service.FundingFacadeService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Validated
public class FundingController {

    private final FundingFacadeService fundingFacadeService;

    @PostMapping("/products/{product_id}/funding")
    public FundingResponse productFunding(@RequestAttribute Long memberId,
                                          @PathVariable(name= "product_id") @Positive Long productId,
                                          @RequestBody @Valid FundingRequest fundingRequest) {
          return fundingFacadeService.productFunding(memberId, productId, fundingRequest);
    }

    @GetMapping("/products/member/fundingList")
    public List<MemberFundingResponse> getMemberFundingList(@RequestAttribute Long memberId) {
        return fundingFacadeService.getProductFundingList(memberId);
    }

}
