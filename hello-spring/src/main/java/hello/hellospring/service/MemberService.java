package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// service : 비즈니스 로직을 작성한다

// service를 만드려면 repository가 필요함

@Service // 스프링 컨테이너에 MemberService를 등록해준다
public class MemberService {

    private final MemberRepository memberRepository;

    @Autowired // DI 주입시켜준다
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository; // 외부에서 memberRepository 넣어주기 -> 테스트작성 위함
    }

    /**
     * 회원가입 서비스
     */
    public Long join(Member member) { // 단축키 : cmd + opt + v : 리턴
        /**
         * 중복체크 방법1. Optional 사용 full 버전
         */
//        // 저장 하기 전 : 같은 이름이 있는 중복 회원 체크
//        Optional<Member> result = memberRepository.findByName(member.getName());
//        // null가능성을 위해 Optional 로 감싸고, Optional을 통해 ifPresent사용 가능함
//        result.ifPresent(m -> {
//            throw new IllegalStateException("이미 존재하는 회원입니다."); // 예외처리
//        });
        /**
         * 중복체크 방법2. Optional 사용 short 버전
         */
        // 저장 하기 전 : 같은 이름이 있는 중복 회원 체크
        validateDuplicateMember(member); //<- ctrl + opt + m 으로 뽑아낸 메서드이다

        // 아래 부터 : 중복회원이 없으므로 여기 올 수 있고, 저장 로직 수행됨
        // 위 validateDuplicate 통과해야 저장이 된다
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> { // null가능성을 위해 Optional 로 감싸고, Optional을 통해 ifPresent사용 가능함
                    throw new IllegalStateException("이미 존재하는 회원입니다."); // -> test로 검증해보기 !!
                });
    }
    /**
     * 전체회원조회 서비스
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }
    /**
     * single 회원조회 서비스
     */
    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
