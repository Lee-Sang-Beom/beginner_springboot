package hello.hellospring;

import hello.hellospring.repository.*;
import hello.hellospring.service.MemberService;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

// 스프링이 실행될 때, Configuration을 먼저 읽고, config 내부의 @Bean annotation으로 설정된 요소를 스프링이 스프링 컨테이너에 스프링 빈으로 등록한다.
@Configuration
public class SpringConfig {

    private DataSource dataSource;
    private EntityManager em;
    // public SpringConfig(DataSource dataSource){
    //     this.dataSource = dataSource;
    // }
    @Autowired
     public SpringConfig(EntityManager em){
        this.em = em;
     }

    // @Bean: 스프링 빈을 등록할 것이라는 annotation
    @Bean
    public MemberService memberService(){
        // memberSerivce 인스턴스를 반환하여, memberSerivce 인스턴스를 스프링 빈으로 등록 (스프링 컨테이너에)
        // 이 때, MemberSerivce 인스턴스를 생성하기 위해서, MemberService의 생성자는 mmemoryMemberRepoitory 인스턴스를 요구한다.
        return new MemberService(memberRepository());
    }
    @Bean
    public MemberRepository memberRepository(){
        // 구현체를 리턴해야함. 인터페이스 넣으면 안됨
        // return new MemoryMemberRepository();
        // return new JdbcMemberRepository(dataSource);
        // return new JdbcTemplateMemberRepository(dataSource);
        return new JpaMemberRepository(em);
    }
}
