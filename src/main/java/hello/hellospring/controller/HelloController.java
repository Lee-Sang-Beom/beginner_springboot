package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

// 1. spring에서 컨트롤러는 @Controller라고 작성해주어야 함
@Controller
public class HelloController {

    // 2. web application에서 /hello 경로로 들어오면 이 메소드를 호출해줌
    @GetMapping("hello")
    public String hello(Model model){
        // 데이터를 관리하는 모델에 {key:"data", value: "hello spring"} 추가
        model.addAttribute("data","hello spring!");
        
        // resources/templates/hello.html과 연결하는 부분임.
        // 즉, 해당 return문은 hello.html을 연결시켜줄테니, 이걸 찾아서 해당 화면을 렌더링 하라는 뜻임
        return "hello";

    }
}
