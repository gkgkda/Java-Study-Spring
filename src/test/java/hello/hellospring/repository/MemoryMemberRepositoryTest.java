package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

class MemoryMemberRepositoryTest {      // Class단에서 Run하면 전체 Test 가능

    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach                      // 테스트는 메소드 순서를 보장하지 않음(오류 발생 가능성)
    public void afterEach() {       // 때문에 각 테스트마다 클린업 코드를 작성해야 함
        repository.clearStore();
    }

    @Test
    public void save() {
        Member member = new Member();
        member.setName("spring");

        repository.save(member);

        Member result = repository.findById(member.getId()).get();
//        System.out.println("result = " + (result == member));     // 검증 방법(1)
//        Assertions.assertEquals(member, result);                  // 방법(2)
//        Assertions.assertEquals(member, null);
        assertThat(member).isEqualTo(result);      // 가장 많이 쓰는 방법
    }

    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();      // shift + F6으로 쉽게 rename
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();

        assertThat(result).isEqualTo(member1);      // "'result'의 값은 'member1'과 같다."
    }

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);
    }
}
