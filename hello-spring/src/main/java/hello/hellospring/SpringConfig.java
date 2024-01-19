package hello.hellospring;

import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import hello.hellospring.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Component scan vs java로 직접 등록
 * @Configuration 을 이용해 컴포넌트 스캔 없이 직접 빈에 등록가능
 */
@Configuration // 이거 쓰고 Bean 을 이용해 스프링 컨테이너에 등록시켜줄수있다
public class SpringConfig {

    @Bean // 스프링 빈에 등록 하게된다
    public MemberService memberService () {
        return new MemberService(memberRepository()); // MemberRepository 파라미터가 필요
    }

    @Bean // 스프링 빈에 등록 하게된다
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

}
