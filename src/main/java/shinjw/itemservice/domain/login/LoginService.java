package shinjw.itemservice.domain.login;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import shinjw.itemservice.domain.member.Member;
import shinjw.itemservice.domain.member.MemberRepository;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final MemberRepository memberRepository;

    /**
     * @return null이면 로그인 실패
     */
    public Member longin(String loginId, String password) {
        return memberRepository.findByLoginId(loginId)
                .filter(member -> member.getPassword().equals(password))
                .orElse(null);
    }
}
