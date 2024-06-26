package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

// controller라는 annotation이 있으면, 스프링은 동작할 때 MemberController 객체를 생성하여 가지고있는다.
// 이를, spring container에서, spring bean이 관리된다고 표현한다.
@Controller
public class MemberController {

    // 스프링이 관리를 하게되면, 스프링 컨테이너에 특정 요소를 다 등록하고, 이후 전부다 스프링 컨테이너에서 요소를 받아서 사용하여야한다.

    // 여기서 사용하는 memberService는 여러 인스턴스를 생성할 필요가 없다. (하나만 생성해놓고 공용으로 쓰면됨)
    // private final MemberService memberService = new MemberService();

    // 이제, 스프링 컨테이너에 MemberService 인스턴스를 1개만 등록해보자.
    private final MemberService memberService;


    // 생성자에 @Autowired가 있으면, 스프링이 연관된 객체를 스프링 컨테이너에서 찾아서 넣어준다.
    // 이렇게 객체 의존관계를 외부에서 넣어주는 것을 DI(Dependency Injection), 의존성 주입이라 한다.
    // 이전 테스트에서는 개발자가 직접 주입했고, 여기서는 @Autowired에 의해 스프링이 주입해준다.
    @Autowired
    public MemberController(MemberService memberService) {

        // 1. 스프링은 동작할 때 MemberController 객체를 생성한다.
        // 2. 이 때, 이 생성자를 호출한다.
        // 3. 생성자에 @Autowired이 있으면, memberService를 스프링이 스프링 컨테이너에 있는 memberService를 가져와서 연결시켜준다.
        this.memberService = memberService;
        System.out.println("memberService is ============> " + memberService.getClass());
    }

    @GetMapping("/members/new")
    public String createForm(){
        return "members/createMemberForm";
    }

    @GetMapping("/members")
    public String list(Model model){
        List<Member> members = memberService.findMembers();
        System.out.println(members);
        model.addAttribute("members", members);
        return "members/memberList";
    }

    // /members/new 경로에서 발생한 POST 요청이 있는지를 검사하고, 있으면 아래의 create 메소드 실행
    @PostMapping("/members/new")
    public String create(MemberForm form){
        Member member = new Member();
        member.setName(form.getName());
        memberService.join(member);

        // 홈 화면으로 사용자 이동
        // (example: /blog로 이동) -> redirect:/blog
        return "redirect:/";
    }

}
