package com.study.funding.exception.entity.prodcut;

import com.study.funding.error.ErrorCode;
import com.study.funding.exception.entity.EntityNotFoundException;

public class ProductNotFoundException extends EntityNotFoundException {

    private final ErrorCode errorCode;

    public ProductNotFoundException(ErrorCode errorCode) {
        super(errorCode);
        this.errorCode = errorCode;
    }
}
