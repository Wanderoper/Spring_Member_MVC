package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

/**
 * 테스트할때는 과감하게 한글로 적어서 해도 된다!! ==> build될 때 코드에 포함되지 않기 때문임
 *
 * 테스트 추천 : given - when - then 패턴을 base로 이용하기 !!! ****** 중요 ********
 *
 * 테스트는 정상 경우 가 아닌 예외 경우 까지 확인해보는게 제대로 테스트 하는 방법임
 */

class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository; // clear 해주기 위해 MemoryMember 가져와야함

    @BeforeEach
    public void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository); // Dependency injection 이다
    }
    @AfterEach
    public void afterEach() {
        memberRepository.clearStore();
    }

    @Test
    void 회원가입() {
        // given
        Member member = new Member();
        member.setName("spring");

        // when
        Long saveId = memberService.join(member);

        // then
        Member findMember = memberService.findOne(saveId).get();
        Assertions.assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복_회원_예외() {
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring"); // <- 중복 회원 예외 테스트
        //when
        memberService.join(member1); // member2 join 시 예외 되는지 확인
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));

        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

        /**
         * try catch 문 작성 한 경우
         */
/*        try {
            memberService.join(member2);
            fail();
        } catch (IllegalStateException e) {
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        }
*/
        //then
    }
//    @Test
//    public void 중복_회원_예외() {
//        // given
//        Member member1 = new Member();
//        member1.setName("spring");
//
//        Member member2 = new Member();
//        member1.setName("spring"); // <- 중복 회원 예외 테스트
//
//        // when
//        memberService.join(member1); // member1을 가입시킴
//        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2)); // 예외를 발생시킴
//
//
//        // then
//    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}