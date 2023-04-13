package hello.hellospring.service;

import hello.hellospring.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration      // 해당 annotation 인식 후
public class SpringConfig {

    private final MemberRepository memberRepository;

    @Autowired  // 생성자가 하나일 경우 생략 가능
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    //    private EntityManager em;
//
//    @Autowired
//    public SpringConfig(EntityManager em) {
//        this.em = em;
//    }

//    @Autowired DataSource dataSource;      // 방법1

    /*@Autowired                          // 방법2
    private DataSource dataSource;

    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }*/

    @Bean       // Spring Bean 등록 후
    public MemberService memberService() {      // 해당 logic 호출
        return new MemberService(memberRepository);
    }

    // 컴포넌트 스캔을 쓰지 않을 경우
/*    @Bean
    public TimeTraceAop timeTraceAop() {
        return new TimeTraceAop();
    }*/

//    @Bean
//    public MemberRepository memberRepository() {
//        return new MemoryMemberRepository();
//        return new JdbcMemberRepository(dataSource);
//        return new JdbcTemplateMemberRepository(dataSource);
//        return new JpaMemberRepository(em);
//    }
}
