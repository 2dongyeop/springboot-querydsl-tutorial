package io.dongvelop.springbootquerydsltutorial.auth;

import io.dongvelop.springbootquerydsltutorial.member.Member;
import io.dongvelop.springbootquerydsltutorial.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class AuthenticationUtil {

    private final MemberRepository memberRepository;

    public Member getContext() {
        final Member member = memberRepository.findById(1L).orElseThrow(() -> {
            log.info("memberId[1L] not found");
            return new IllegalArgumentException();
        });

        log.info("member[{}]", member);
        return member;
    }
}
