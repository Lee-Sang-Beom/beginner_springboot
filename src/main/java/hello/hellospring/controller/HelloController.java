package hello.hellospring.controller;

import org.springframework.boot.SpringApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import hello.hellospring.HelloSpringApplication;

@Controller
public class HelloController {

    public static void main(String[] args) {
		SpringApplication.run(HelloSpringApplication.class, args);
	}
    
    @GetMapping("hello")
    public String hello(Model model){
        model.addAttribute("data","hello spring!");
        return "hello";
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model){
        model.addAttribute("name", name);
        return "hello-template";
    }
}
