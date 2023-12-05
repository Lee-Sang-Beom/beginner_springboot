package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model){
        model.addAttribute("data","hello spring!");
        return "hello";
    }

    // 추가: MVC
    @GetMapping("hello-mvc")

    // 외부에서 파라미터를 받으려면, @RequestParams 사용 
    public String helloMvc(@RequestParam("name") String name, Model model){
        // 파라미터로 넘어온 name을 model에 "name"이라는 key로 전달
        model.addAttribute("name", name);

        // resources/templates/[return String]와 연결
        return "hello-template";
    }
}
