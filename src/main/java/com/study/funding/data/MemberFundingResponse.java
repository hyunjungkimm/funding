package com.study.funding.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberFundingResponse {
    private Long fundingId;
    private Long fundingPrice;
    private Long memberId;
    private Long productId;
    private String productName;
    private LocalDate fundingDate;
}
