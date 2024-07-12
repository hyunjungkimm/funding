package com.study.funding.service;

import com.study.funding.data.MemberFundingResponse;
import com.study.funding.entity.Funding;
import com.study.funding.entity.Member;
import com.study.funding.entity.Product;
import com.study.funding.repository.FundingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class FundingService {

    private final FundingRepository fundingRepository;

    @Transactional
    public void productFunding(Funding funding) {
        fundingRepository.save(funding);
    }

    @Transactional(readOnly = true)
    public List<MemberFundingResponse> getProductFundingList(Member member) {
        List<Funding> fundingList =  fundingRepository.findAllByMember(member);
        List<MemberFundingResponse> memberFundingResponseList = new ArrayList<>();
        for(Funding funding : fundingList) {
            MemberFundingResponse memberFundingResponse = new MemberFundingResponse();
            memberFundingResponse.setFundingId(funding.getFundingId());
            memberFundingResponse.setFundingPrice(funding.getFundingPrice());
            memberFundingResponse.setFundingDate(funding.getFundingDate());
            memberFundingResponse.setMemberId(funding.getMember().getMemberId());
            memberFundingResponse.setProductId(funding.getProduct().getProductId());
            memberFundingResponse.setProductName(funding.getProduct().getProductName());
            memberFundingResponseList.add(memberFundingResponse);
        }
        return memberFundingResponseList;
    }

}
