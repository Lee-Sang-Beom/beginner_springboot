package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(){
        /**
         * 1. URL 요청이 들어오고, 내장 Tomcat 서버가 최초로 요청을 받아 해당 요청을 스프링에게 넘긴다.
         * 2. 스프링은 스프링 컨테이너 내부에, home.html과 맵핑된 컨트롤러가 있는지 먼저 찾아본다. (여기서는 HomeController에 GetMapping("/") 부분이 있음
         * 3. 만약, 매핑된 컨트롤러가 있으면, 매핑된 컨트롤러 호출하고, 그 내부에서 return한 해당 html파일을 반환한다 (resources/templates/home.html)
         * 4. 만약 매핑된 컨트롤러가 없으면, 스프링은 resources/static/home.html이 있는지를 확인한다.
         */
        return "home";

    }
}
