package com.study.funding.validation;

import org.junit.jupiter.api.Test;
import org.springframework.validation.DefaultMessageCodesResolver;
import org.springframework.validation.MessageCodesResolver;

import static org.assertj.core.api.Assertions.assertThat;

public class MessageCodesResolverTest {

    MessageCodesResolver codesResolver = new DefaultMessageCodesResolver();

    @Test
    void messageCodesResolverObject() {
        String[] messageCodes = codesResolver.resolveMessageCodes("required", "funding");
        assertThat(messageCodes).containsExactly( "required.funding", "required");
    }

    @Test
    void messageCodesResolverFiled() {
        String[] messageCodes = codesResolver.resolveMessageCodes("required", "funding", "fundingPrice", Integer.class);
        assertThat(messageCodes).containsExactly(
                "required.funding.fundingPrice",
                        "required.fundingPrice",
                        "required.java.lang.Integer",
                        "required");
    }
}
