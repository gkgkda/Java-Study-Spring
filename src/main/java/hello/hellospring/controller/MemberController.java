package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {

    @PostMapping("/members/new")
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());

//        System.out.println("member = " + member.getName());

        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
        System.out.println("memberService = " + memberService.getClass());
        /*
        memberService = class hello.hellospring.service.MemberService$$SpringCGLIB$$0
         * AOP 사용 시 Spring 컨테이너는 CGLIB 라는 라이브러리를 통해 가짜 memberService를 생성 (프록시 기술)
         * helloController를 Run 하면 가짜 memberService 를 우선 호출하고 AOP를 실행
         * 이어서 joinPoint.proceed() 가 수행되고 이때 실제 memberService가 호출되는 방식
         */
    }

    @GetMapping("members/new")
    public String createForm() {
        return "members/createMemberForm";
    }
}
