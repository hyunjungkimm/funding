package com.study.funding.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.study.funding.data.ProductFundingResponse;
import com.study.funding.data.ProductResponse;
import com.study.funding.entity.Member;
import com.study.funding.repository.MemberRepository;
import com.study.funding.service.ProductService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(ProductController.class)
@MockBean(JpaMetamodelMappingContext.class)
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ProductService productService;

    @MockBean
    private MemberRepository memberRepository;


    @DisplayName("펀딩 제품 전체 조회하기 - getAllProducts")
    @Test
    void getAllProducts() throws Exception {

        Long memberId = 1L;


        when(
                memberRepository.findById(memberId)
        ).thenReturn(
                Optional.ofNullable(makeMember())
        );

        when(
                productService.getProducts()
        ).thenReturn(
                productResponseList()
        );

        mockMvc.perform(get("/api/v2/products")
                .header("X-MEMBER-ID", memberId)
                .contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(productResponseList())));


    }

    @DisplayName("펀딩 제품 전체 조회하기 - getAllProductWithPAgeByQueryMethod")
    @Test
    void getAllProductWithPAgeByQueryMethod() throws Exception {

        Long memberId = 1L;
        Page<ProductResponse> productResponsePage = new PageImpl<>(productResponseList());

        when(
                memberRepository.findById(memberId)
        ).thenReturn(

                Optional.ofNullable(makeMember())
        );

        when(
                productService.getProductProgress(0, 5)
        ).thenReturn(
                productResponsePage
        );


        mockMvc.perform(get("/api/v3/products")
                        .header("X-MEMBER-ID", memberId)
                        .param("size", String.valueOf(0))
                        .param("page", String.valueOf(5))
                        .contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(productResponsePage)));
    }


    @DisplayName("펀딩 제품 전체 조회하기 - getAllProduct")
    @Test
    void getAllProduct() throws Exception {

        Long memberId = 1L;
        Slice<ProductResponse> productResponseSlice = new PageImpl<>(productResponseList());

        when(
                memberRepository.findById(memberId)
        ).thenReturn(

                Optional.ofNullable(makeMember())
        );

        when(
                productService.getAllProducts(PageRequest.of(0,5))
        ).thenReturn(
                productResponseSlice
        );

        String params = String.format("?page=%d&size=%d", 0, 5);

        mockMvc.perform(get("/api/v4/products"+params)
                        .header("X-MEMBER-ID", memberId)
                        .contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(productResponseSlice)));
    }


    private Member makeMember() {
        return Member.builder()
                .memberId(1L)
                .memberName("jung")
                .build();
    }

    private List<ProductResponse> productResponseList() {
        ProductFundingResponse ProductFundingResponse = new ProductFundingResponse(1L);

        ProductResponse productResponse = ProductResponse.builder()
                .productId(1L)
                .productName("제품명")
                .targetFundingPrice(10000L)
                .totalFundingPrice(0L)
                .fundingMemberNumber(0)
                .fundingList(List.of(ProductFundingResponse))
                .build();

        return List.of(productResponse);
    }


}