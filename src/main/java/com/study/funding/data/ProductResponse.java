package com.study.funding.data;

import com.study.funding.entity.Funding;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductResponse {
    private Long productId;
    private String productName;
    private Long targetFundingPrice;
    private Long totalFundingPrice;
    private LocalDate startDate;
    private LocalDate finishDate;
    private int fundingMemberNumber;
    private List<ProductFundingResponse> fundingList = new ArrayList<>();
}
