package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;
@Repository
public class MemoryMemberRepository implements MemberRepository {

    // 회원 정보를 메모리에 저장하기 위해 선언
    // key는 회원의 아이디(Long), value값은 Member 타입임
    // 해당 코드는 동시성 문제 발생
    private static Map<Long, Member> store = new HashMap<>();

    // seq: 0,1,2,...로 키값을 생성함.
    private static long sequence = 0L;

    @Override
    public Member save(Member member) {

        // 멤버값에 id 세팅: seq값을 먼저 올린 값을 id로 저장
        member.setId(++sequence);

        // 저장소(맵객체) 에 memberId(key), member(value) 저장
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        // 아이디 찾기(꺼내기)

        // 결과가없으면 null로 나올수있음.
        // return stort.get(id)만을 쓰기보다는 이것을, ofNullable로 감싸주면, null을 Optional로 감싸서 반환됨
        // (클라이언트가 뭔가를 할 수 있게 됨)
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public List<Member> findAll() {

        // store의 모든 값들을 array형태로 반환
        return new ArrayList<>(store.values());
    }

    @Override
    public Optional<Member> findByName(String name) {
        // filter => name이 같은 걸 찾아서 반환한다.
        // 끝까지 돌렸는데도 못찾으면 Optional이 null을 감싸서 반환
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
    }

    public void clearStore() {
        store.clear();
    }
}