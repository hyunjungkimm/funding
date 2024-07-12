package com.study.funding.service;

import com.study.funding.entity.Member;
import com.study.funding.exception.entity.user.UserNotFoundException;
import com.study.funding.repository.MemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MemberServiceTest {

    @InjectMocks
    private MemberService memberService;

    @Mock
    private MemberRepository memberRepository;

    private Member makeMember(Long memberId) {
        return Member.builder()
                .memberId(memberId)
                .memberName("jung")
                .build();
    }

    @DisplayName("멤버 조회 - 성공")
    @Test
    void getMember_success() {

        Long memberId = 1L;

        when(
                memberRepository.findById(memberId)
        ).thenReturn(
                Optional.ofNullable(makeMember(memberId))
        );

        Member member = memberService.getMember(memberId);

        assertThat(member.getMemberId()).isEqualTo(memberId);
    }
    @DisplayName("멤버 조회 - 실패")
    @Test
    void getMember_fail() {

        Long memberId = 1L;

        when(
                memberRepository.findById(memberId)
        ).thenReturn(
                Optional.empty()
        );

        assertThrows(UserNotFoundException.class, () -> memberService.getMember(memberId));

    }

}