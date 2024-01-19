package hello.hellospring.controller;

import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @Controller 역할 : 스프링 컨테이너에서 등록해둔다 -> 컨테이너가 관리
 */
@Controller  // 스프링 실행 시, 컨테이너에 해당 클래스를 객체화하여 컨테이너에 넣어두고 스프링이 관리한다
public class MemberController {

    private final MemberService memberService;

    @Autowired // 역할 : 스프링이 컨테이너에 있는 memberService를 연결해준다 -> 문제점: memberService가 빈에 등록되어있지 않음!!
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
}
