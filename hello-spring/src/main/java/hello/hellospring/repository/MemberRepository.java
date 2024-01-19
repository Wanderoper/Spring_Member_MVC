package hello.hellospring.repository;
// repository는 회원객체 (domain)을 저장하는 저장소이다
import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository { // 인터페이스이므로 다른 클래스에서 상속받아 implements 해야한다
    Member save(Member member); // 1. 저장기능 - save 하면 repository에 저장된다
    Optional<Member> findById(Long id); // 2. 조회기능 - 저장된 id를 이용하여 찾는다 - 찾는 값이 없을 수 있으므로 optional 사용
    Optional<Member> findByName(String name); // 3. 조회기능 - 저장된 name을 이용하여 찾는다 - 없을 수 있으므로 optional 사용
    List<Member> findAll(); // 4. 조회기능 - 모든 저장된 회원들을 불러온다


}
