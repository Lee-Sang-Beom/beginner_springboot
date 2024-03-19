package hello.hellospring.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
class MemberServiceTest {

    // 헷갈리지 말아야 할것 (from 커뮤니티)
    // repository의 경우 실제 DB와 밀접하게 통신하는 레이어(계층)이라고 보시면 됩니다.

    // Service의 경우, 애플리케이션의 비즈니스 로직을 처리하는 Layer입니다.
    // 가령, 회원가입을 할 때 회원가입(Join)에서 실명 확인 서비스 api를 통해 검증된 사람만 회원가입을 해야된다라는 요건이 추가되었다고 가정해보겠습니다.
    // 이 때, 이 실명 확인 서비스는 Repository Layer가 아닌, Service에서 처리가 되어야 하는 로직입니다.

    MemberService memberService;

    // memberService 안에서 사용되는 MemoryMemberRepository와 다른 객체임. 그래서 이거 말고 , memberService 안에서 사용되는 MemoryMemberRepository를 사용할 것임
    // MemoryMemberRepository memberRepository = new MemoryMemberRepository();
    MemoryMemberRepository memberRepository;

    // 동작하기 전에 실행되는 구문
    // 테스트할때마다 beforeEach가 생성됨
    @BeforeEach
    public void beforeEach(){
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }


    // 테스트가 1개가 끝나면, 깔끔하게 리포지토리 데이터를 clear해줘야함
    // 테스트 순서는 항상 동일하지 않으므로, 서로 의존적이면 안됨
    @AfterEach
    public void afterEach(){
        memberRepository.clearStore();
    }

    @Test
    void join() {
        // 회원가입

        // given
        Member member = new Member();
        member.setName("spring");

        // when
        Long saveId= memberService.join(member);

        // then
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    void dupMemberException(){
        // 중복회원예외

        // given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        // when 
        memberService.join(member1); // 최초 저장은 문제 없어야 함

        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2)); // 두번째 콜백으로 넘긴 함수가 실행되면서 1번째로 전달한 예외가 터져주는지 확인하는 구문
//        assertThrows(NullPointerException.class, () -> memberService.join(member2)); // null pointer 에러가 터지는지 기대함 (아니면 테스트 실패)


        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다."); // 에러 안나면 성공



//        try{
//            memberService.join(member2); // memberRepository에 "spring" 데이터가 있으니 오류 띄워야 함
//            fail();
//        } catch (IllegalStateException e){
//            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
//        }
    }

    @org.junit.jupiter.api.Test
    void findMembers() {
    }

    @org.junit.jupiter.api.Test
    void findMember() {
    }
}