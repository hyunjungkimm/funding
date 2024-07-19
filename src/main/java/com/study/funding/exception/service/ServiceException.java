package com.study.funding.exception.service;

import com.study.funding.error.ErrorCode;
import com.study.funding.exception.BusinessException;
import lombok.Getter;

@Getter
public class ServiceException extends BusinessException {

    private final ErrorCode errorCode;

    public ServiceException(ErrorCode errorCode) {
        super(errorCode);
        this.errorCode = errorCode;
    }
}
