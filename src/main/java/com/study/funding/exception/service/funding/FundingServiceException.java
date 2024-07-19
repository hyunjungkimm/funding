package com.study.funding.exception.service.funding;

import com.study.funding.error.ErrorCode;
import com.study.funding.exception.service.ServiceException;
import lombok.Getter;

@Getter
public class FundingServiceException extends ServiceException {

    private final ErrorCode errorCode;

    public FundingServiceException(ErrorCode errorCode) {
        super(errorCode);
        this.errorCode = errorCode;
    }
}
