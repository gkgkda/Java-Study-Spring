package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository;    // 2. (1)에서 생성한 repo를 이곳에 넣고

    @BeforeEach
    public void beforeEach() {
        memberRepository = new MemoryMemberRepository();    // 1. 각 테스트를 실행할 때마다 'MemoryMemberRepository' 생성
        memberService = new MemberService(memberRepository);    // 3. 여기서 (1)의 'memberRepository'를 MemberService의 'memberRepository'에 넣음
                                                                // 즉, 객체를 따로 생성하지 않고 같은 repo를 사용하게 됨

    }

    @AfterEach                      // 테스트는 메소드 순서를 보장하지 않음(오류 발생 가능성)
    public void afterEach() {       // 때문에 각 테스트마다 클린업 코드를 작성해야 함
        memberRepository.clearStore();
    }

    @Test
    void 회원가입() {
        // given
        Member member = new Member();
        member.setName("hello");

        // when (검증할 기능)
        Long saveId = memberService.join(member);

        // then (검증 결과)
        Member searchingMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(searchingMember.getName());
    }

    @Test
    public void 중복_회원_예외() {
        // given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        // when
        memberService.join(member1);
//        memberService.join(member2);
        // 해당 예외(IllegalState)가 발생해야 함 (when?) -> 우측 logic을 태운다면
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));

        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
/*        try {
            memberService.join(member2);
            fail();
        } catch (IllegalStateException e) {
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다. 12124");   // [Error]
        }*/

        // then

    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}