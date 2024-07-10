package com.study.funding.exception.entity.user;

import com.study.funding.error.ErrorCode;
import com.study.funding.exception.entity.EntityNotFoundException;

public class UserNotFoundException extends EntityNotFoundException {
    private final ErrorCode errorCode;

    public UserNotFoundException(ErrorCode errorCode) {
        super(errorCode);
        this.errorCode = errorCode;
    }
}
