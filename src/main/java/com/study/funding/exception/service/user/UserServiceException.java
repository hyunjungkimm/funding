package com.study.funding.exception.service.user;

import com.study.funding.error.ErrorCode;
import com.study.funding.exception.service.ServiceException;
import lombok.Getter;

@Getter
public class UserServiceException extends ServiceException {

    private ErrorCode errorCode;

    public UserServiceException(ErrorCode errorCode) {
        super(errorCode);
        this.errorCode = errorCode;
    }
}
