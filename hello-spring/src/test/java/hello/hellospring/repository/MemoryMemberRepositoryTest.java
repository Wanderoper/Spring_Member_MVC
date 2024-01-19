package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Scanner;

import static org.assertj.core.api.Assertions.*; // static import 사용 : opt + enter

/* 주의 !!! */
// test 순서는 보장되지 않으므로 -> @Test 끝날때마다 Clear를 해줘야 오류가 안난다
// clear 해주는 방법 -> @AfterEach 사용하기
// 테스트는 서로 순서에 의존적이지 않게 설계해야한다

// 여기서는 구현을 먼저 하고 -> 테스트를 했는데
// 테스트를 먼저 만들고 -> 구현을 하는것을 TDD (Test Driven Development) 라고 한다

class MemoryMemberRepositoryTest {
    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach // 중요내용 : 테스트 마다 db(여기서는 store 라는 메모리) 를 비워준다 -> 정상적으로 아래 내용을 테스트하기 위해서
    public void afterEach() {
        repository.clearStore();
    }

    @Test // save 메서드 테스트
    public void save() {
        Member member = new Member();
        member.setName("spring");

        repository.save(member); // 저장할때 아이디가 세팅된다

        Member result = repository.findById(member.getId()).get(); // 저장된 멤버 가져오기
        /* 검증 해보기 */
        System.out.println("result = " + (result == member)); // 이렇게 검증해도 되고 assert 사용해도 된다 -> 실무는 assert
        // 위와 같이 검증해보면 되는데 매번 콘솔찍기 어려우므로 Assertion 을 사용하면 편하다
        assertThat(member).isEqualTo(result); // 실무에서는 test 통과못하면 build 못하게 막아버린다
    }
    @Test // findByName 메서드 테스트
    public void findByName() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1); // save 시 id는 자동 채번해줌

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2); // save 시 id는 자동 채번해줌

        Member result = repository.findByName("spring1").get();

        // 테스트
        assertThat(result).isEqualTo(member1);

    }
    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring1");
        repository.save(member2);

        // 저장 완료, 이제 테스트

        List<Member> result = repository.findAll(); // 이게 Actual

        assertThat(result.size()).isEqualTo(2); // 2가 expected
    }

}
//
//
//    }
//
//    @Test
//    public void findAll() {
//        Member member1 = new Member();
//        member1.setName("spring1");
//        repository.save(member1);
//
//        Member member2 = new Member();
//    }
