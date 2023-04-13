package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest     // Spring 컨테이너와 테스트를 함께 실행
@Transactional      // 우선 트랜잭션을 실행하고 테스트가 끝나면 항상 rollback (DB에 데이터 남지 X)
class MemberServiceIntegrationTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

    @Test
//    @Commit     // DB에 적용
    void 회원가입() {
        // given
        Member member = new Member();
        member.setName("spring");

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
    }
}