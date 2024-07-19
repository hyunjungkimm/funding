package com.study.funding.exception.entity;

import com.study.funding.error.ErrorCode;
import com.study.funding.exception.BusinessException;
import lombok.Getter;

@Getter
public class EntityNotFoundException extends BusinessException {

    private final ErrorCode errorCode;

    public EntityNotFoundException(ErrorCode errorCode) {
        super(errorCode);
        this.errorCode = errorCode;
    }
}
