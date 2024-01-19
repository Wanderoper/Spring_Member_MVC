package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

/**
 * @Controller 역할 : 스프링 컨테이너에서 등록해둔다 -> 컨테이너가 관리
 */
@Controller  // 스프링 실행 시, 컨테이너에 해당 클래스를 객체화하여 컨테이너에 넣어두고 스프링이 관리한다
public class MemberController {

    private final MemberService memberService;

    // 권장하는 스타일 : 생성자 주입을 쓰는게 좋다
    @Autowired // 역할 : 스프링이 컨테이너에 있는 memberService를 연결해준다 -> 문제점: memberService가 빈에 등록되어있지 않음!!
    public MemberController(MemberService memberService) { // <- 생성자 주입
        this.memberService = memberService;
    }

    @GetMapping("/members/new") // 회원등록 (입력)
    public String createForm() {
        return "members/createMemberForm"; // template으로 이동
    }

    @PostMapping("/members/new") // 회원등록 (생성)
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName()); // member 생성

        memberService.join(member); // member를 서비스로 등록해준다

        return "redirect:/"; // 회원가입이 끝나면 홈화면으로 돌아간다
    }

    @GetMapping("/members") // 회원목록 조회
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members); // model 에 등록 -> 회원목록을
        // 등록된 model 에서 데이터를 뿌려줄 템플릿 memberList를 꾸미기
        return "members/memberList";
    }
}
