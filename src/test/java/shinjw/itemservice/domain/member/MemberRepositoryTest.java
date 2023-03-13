package shinjw.itemservice.domain.member;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

class MemberRepositoryTest {

    private static final Map<Long, Member> store = new HashMap<>(); // static 사용
    private static long sequence = 0L; // static 사용

    @AfterEach
    void afterEach() {
        clearStore();
    }

    @Test
    void 저장() {
        Member member = new Member();
        member.setName("신제우");
        member.setLoginId("shinjw");
        member.setPassword("testpasswd");

        member.setId(++sequence);
        store.put(member.getId(), member);

        assertThat(store.get(member.getId())).isEqualTo(member);
    }

    @Test
    void 로그인아이디로검색() {
        Member member = new Member();
        member.setName("신제우");
        member.setLoginId("shinjw");
        member.setPassword("testpasswd");
        member.setId(++sequence);
        store.put(member.getId(), member);

        Optional<Member> shinjw = findByLoginId("shinjw");
        Optional<Member> notExist = findByLoginId("notExist");

        assertThat(shinjw).isEqualTo(Optional.of(member));
        assertThat(notExist).isEqualTo(Optional.empty());
    }

    @Test
    Optional<Member> findByLoginId(String loginId) {
        List<Member> members = findAll();
        return members.stream()
                .filter(member -> loginId.equals(member.getLoginId()))
                .findFirst();
    }

    @Test
    List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    @Test
    void clearStore() {
        store.clear();
    }
}