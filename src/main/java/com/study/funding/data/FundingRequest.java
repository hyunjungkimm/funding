package com.study.funding.data;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class FundingRequest {

    @NotNull
    @Min(value = 1000)
    @Positive
    private Long fundingPrice;
}
