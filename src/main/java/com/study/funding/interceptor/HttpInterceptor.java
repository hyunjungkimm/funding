package com.study.funding.interceptor;


import com.study.funding.entity.Member;
import com.study.funding.error.ErrorCode;
import com.study.funding.exception.entity.user.UserNotFoundException;
import com.study.funding.exception.service.user.UserServiceException;
import com.study.funding.repository.MemberRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Optional;


@Slf4j
@RequiredArgsConstructor
@Component
public class HttpInterceptor implements HandlerInterceptor {

    private final MemberRepository memberRepository;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String memberId =  request.getHeader("X-MEMBER-ID");
        log.info("memberId = {}", memberId);

        if(memberId != null){
            Optional<Member> member = memberRepository.findById(Long.parseLong(memberId));

            if(member.isPresent()){
                request.setAttribute("memberId", member.get().getMemberId());
            }else {
                throw new UserNotFoundException(ErrorCode.NOT_SIGNED_UP_MEMBER);
            }
            return true;
        }else {
            throw new UserServiceException(ErrorCode.NOT_EXITS_MEMBER_ID_HEADER);
        }
    }

}
