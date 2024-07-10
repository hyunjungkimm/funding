package com.study.funding.data;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class FundingRequest {

    @NotNull(message = "펀딩 금액은 필수입니다.")
    @Min(value = 1000, message = "펀딩 금액은 최소 1000원 이상이여야 합니다.")
    @Positive(message = "펀딩 금액은 양수여야 합니다.")
    private Long fundingPrice;
}
