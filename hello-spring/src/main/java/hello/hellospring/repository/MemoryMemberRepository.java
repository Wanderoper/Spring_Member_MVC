package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

//@Repository // 스프링에 등록
public class MemoryMemberRepository implements MemberRepository {
    // 저장을 하기 위해 List 를 이용한다
    private static Map<Long, Member> store = new HashMap<>(); // DB가 확정되지 않아서, 메모리에 저장하는 시나리오이다.
    private static long sequence = 0L; // key 값을 생성하기 위한 변수임 - 단순하게 사용한다 = long type으로 사용하기 위함


    @Override
    public Member save(Member member) {
        member.setId(++sequence); // id 값을 세팅해준다
        store.put(member.getId(), member); // id올려서 저장해주고, store 객체에 저장해준다
        return member; // member는 입력한 값이 저장되고 , id는 시스템이 자동으로 채번한다
    }
    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id)); // 사용자가 id 검색 했을 때 아이디가 없는 즉, null일 경우 클라이언트에서 처리 위함
    }
    @Override
    public Optional<Member> findByName(String name) {
        // lambda 사용법
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
    }
    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear(); // store를 싹 비워준다 즉, db 비우기 -> Test 할때마다 지우기 위해
        }
}

