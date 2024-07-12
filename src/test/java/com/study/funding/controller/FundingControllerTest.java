package com.study.funding.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.study.funding.data.FundingRequest;
import com.study.funding.data.FundingResponse;
import com.study.funding.data.MemberFundingResponse;
import com.study.funding.entity.Member;
import com.study.funding.repository.MemberRepository;
import com.study.funding.service.FundingFacadeService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(FundingController.class)
@MockBean(JpaMetamodelMappingContext.class)
class FundingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private FundingFacadeService fundingFacadeService;

    @MockBean
    private MemberRepository memberRepository;

    @DisplayName("내가 펀딩한 리스트 조회 api - 성공")
    @Test
    public void getMemberFundingList_success() throws Exception {

        Long memberId = 1L;
        MemberFundingResponse memberFundingResponse = MemberFundingResponse.builder()
                .fundingId(1L)
                .fundingPrice(1000L)
                .memberId(1L)
                .productId(1L)
                .productName("product")
                .fundingDate(LocalDate.now())
                .build();

        List<MemberFundingResponse> responseList = new ArrayList<>();
        responseList.add(memberFundingResponse);

        when(
                fundingFacadeService.getProductFundingList(memberId)
        ).thenReturn(
                responseList
        );

        when(
                memberRepository.findById(memberId)
        ).thenReturn(
                Optional.ofNullable(makeMember())
        );

        mockMvc.perform(
                        get("/products/member/fundingList")
                                .header("X-MEMBER-ID", memberId)
                                .contentType(MediaType.APPLICATION_JSON)
                ).andExpect(status().isOk()
                ).andExpect(content().json(objectMapper.writeValueAsString(responseList)));
    }

    @DisplayName("펀딩하기 api - 성공")
    @Test
    public void productFundingTest_success() throws Exception {
        Long memberId = 1L;
        Long productId = 1L;

        FundingRequest fundingRequest = new FundingRequest(1000L);
        FundingResponse fundingResponse = new FundingResponse("펀딩 성공");
        when(
                fundingFacadeService.productFunding(memberId, productId, fundingRequest)
        ).thenReturn(
                new FundingResponse("펀딩 성공")
        );

        when(
                memberRepository.findById(memberId)
        ).thenReturn(
                Optional.ofNullable(makeMember())
        );

        String content = objectMapper.writeValueAsString(fundingRequest);

        mockMvc.perform(
                post("/products/" + productId + "/funding")
                        .header("X-MEMBER-ID", memberId)
                        .content(content)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()
        ).andExpect(content().json(objectMapper.writeValueAsString(fundingResponse)));

    }

    @DisplayName("펀딩하기 api - 실패")
    @Test
    public void productFundingTest_fail() throws Exception {
        Long memberId = 1L;
        Long productId = 1L;

        FundingRequest fundingRequest = new FundingRequest(100L);
        when(
                fundingFacadeService.productFunding(memberId, productId, fundingRequest)
        ).thenReturn(
                new FundingResponse("펀딩 성공")
        );

        when(
                memberRepository.findById(memberId)
        ).thenReturn(
                Optional.ofNullable(makeMember())
        );

        String content = objectMapper.writeValueAsString(fundingRequest);

        mockMvc.perform(
                post("/products/" + productId + "/funding")
                        .header("X-MEMBER-ID", memberId)
                        .content(content)
                        .contentType(MediaType.APPLICATION_JSON)
                ).andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("펀딩금액은 필수이며 1000원 이상이여야 합니다."));

    }


    private Member makeMember() {
        return Member.builder()
                .memberId(1L)
                .memberName("jung")
                .build();
    }


}