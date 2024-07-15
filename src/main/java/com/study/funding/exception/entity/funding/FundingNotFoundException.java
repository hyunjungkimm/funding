package com.study.funding.exception.entity.funding;


import com.study.funding.error.ErrorCode;
import com.study.funding.exception.entity.EntityNotFoundException;

public class FundingNotFoundException extends EntityNotFoundException {

    private ErrorCode errorCode;

    public FundingNotFoundException(ErrorCode errorCode) {
        super(errorCode);
        this.errorCode = errorCode;
    }
}
