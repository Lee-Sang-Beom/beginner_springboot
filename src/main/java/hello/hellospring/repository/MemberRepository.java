package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

// 회원 리포지토리 인터페이스: 회원 객체를 저장하는 저장소
public interface MemberRepository {

    // 회원 저장: 저장 시, 저장한 회원정보 반환
    Member save(Member member);

    // 아이디로 회원 찾는 걸 만듬
    // (Optional: findById로 유저를 못찾고 null이 반환될 수 있음. Optional이라는 것으로 null을 감싸는 방법임)
    Optional<Member> findById(Long id);

    // 이름으로 회원 찾는 걸 만듬
    Optional<Member> findByName(String name);

    List<Member> findAll();

}