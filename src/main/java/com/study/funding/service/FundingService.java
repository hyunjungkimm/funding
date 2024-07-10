package com.study.funding.service;


import com.study.funding.data.FundingRequest;
import com.study.funding.data.FundingResponse;

public interface FundingService {

    FundingResponse productFunding(long userId, long productId, FundingRequest fundingRequest);
}
