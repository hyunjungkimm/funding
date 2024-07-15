package com.study.funding.service;

import com.study.funding.entity.Member;
import com.study.funding.exception.entity.user.UserNotFoundException;
import com.study.funding.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.study.funding.error.ErrorCode.NOT_SIGNED_UP_MEMBER;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional(readOnly = true)
    public Member getMember(Long memberId) {
        return memberRepository.findById(memberId).orElseThrow(() -> new UserNotFoundException(NOT_SIGNED_UP_MEMBER));
    }
}
