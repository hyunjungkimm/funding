package com.study.funding.data;

import lombok.Getter;

@Getter
public enum FundingStatus {
    IN_PROGRESS("펀딩중"),
    COMPLETED("펀딩완료");


    private final String status;

    FundingStatus(String status) {
        this.status = status;
    }
}
