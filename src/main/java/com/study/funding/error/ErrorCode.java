package com.study.funding.error;

import lombok.Getter;

@Getter
public enum ErrorCode {

    NOT_SIGNED_UP_MEMBER("가입된 사용자가 아닙니다.", "NOT_SIGNED_UP_MEMBER"),
    NOT_EXITS_MEMBER_ID_HEADER("Header에 member id가 없습니다.", "NOT_EXITS_MEMBER_ID_HEADER"),
    REQUIRED_VALUE("펀딩금액은 필수이며 1000원 이상이여야 합니다.", "REQUIRED_VALUE"),
    INVALID_INPUT_VALUE("Parameter가 유효하지 않습니다.", "INVALID_INPUT_VALUE");

    private final String message;
    private final String code;

    ErrorCode(String message, String code) {
        this.message = message;
        this.code = code;
    }
}
