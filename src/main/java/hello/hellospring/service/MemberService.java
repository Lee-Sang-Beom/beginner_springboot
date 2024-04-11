package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// 서비스 클래스는 굉장히 비즈니스와 어울리는 기능과 네이밍을 가짐
// 반면 리포지토리 클래스는 단순히 저장소에 넣었다 뺐다 하는 기능과 네이밍을 가짐

// @Service
public class MemberService {
    private final MemberRepository memberRepository;

    // 내부에서 MemberRepository를 new로 생성하는게 아니라, 외부에서 넣어주도록 변경
    // 이것을 dependency injection(DI)라고 함

    // @Autowired
    public MemberService(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }
    /**
     * 회원가입
     *  - 리포지토리에 member 저장 (단, 같은 이름의 중복회원이 있으면 안된다)
     *  - member의 id 반환 (임의)
     */
    public Long join(Member member) {
        validateDuplicatedMember(member); // 중복회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    /**
     * 전체회원 조회
     */
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }

    /**
     * 아이디에 해당하는 유저 반환
     */
    public Optional<Member> findMember(Long memberId){
        return memberRepository.findById(memberId);
    }

    /**
     * 같은 이름의 중복 회원이 있는지 먼저 검사
     */
    private void validateDuplicatedMember(Member member) {
        /**
         * isPresent(): Optional 객체가 값을 포함하고 있는지 여부를 확인하는 데 사용된다. 반환 값은 boolean 형식이며, 값이 존재하면 true를 반환하고, 값이 존재하지 않으면 false를 반환한다.
         * ifPresent(): Optional 클래스는 값이 존재하거나 존재하지 않을 수 있는 컨테이너를 나타내며, 해당 메서드는 Optional 객체가 값을 포함할 때만 지정된 로직을 실행하도록 하는 메서드이다.
         */
        memberRepository.findByName(member.getName()).ifPresent(m -> {
            /**
             * IllegalArgumentException: null이 아닌 인자의 값이 잘못되었을 때
             * IllegalStateException(이거!): 객체 상태가 메서드 호출을 처리하기에 적절치 않을 때
             * NullPointException: null 값을 받으면 안 되는 인자에 null이 전달되었을 때
             * IndexOutOfBoundsException: 인자로 주어진 첨자가 허용 범위를 벗어났을 때
             * ConcurrentModificationException: 병렬적 사용이 금지된 객체에 대한 병렬 접근이 탐지 되었을 때
             * UnsupportedOperationException: 객체가 해당 메서드를 지원하지 않을 때
             */
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });
    }

}
