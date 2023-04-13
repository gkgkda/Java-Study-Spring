package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public class MemberService {
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {   // 객체 따로 생성 X 생성자를 이용해 외부에서 넣어주도록
        this.memberRepository = memberRepository;
    }

    /**
     * 회원 가입
     */
    public Long join(Member member) {
        validateDuplicateMember(member);    // 중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        // Optional<> : (if != null) 등 null값 처리를 간소화할 수 있는 Wrapper 클래스
//        Optional<Member> result = memberRepository.findByName(member.getName());
        memberRepository.findByName(member.getName())       // result는 어차피 Optional일 테니 생략 가능
                .ifPresent(m -> {     // result에 해당 값(member)이 존재한다면, { 명령문 } 실행
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);     // findById 실행 시 memberId를 받아서 return
    }
}
