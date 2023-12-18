# DAY4

### 1. MVC와 템플릿 엔진

- MVC : Model, View, Controller
- mvc와 템플릿 엔진 방식은 템플릿 엔진을 Model, View, Controller 방식으로 쪼개고, HTML을 좀 더 동적인 방식으로 프로그래밍한 후에 렌더링을 한 HTML 파일을 클라이언트에게 전달해주는 방식

#### Controller

```java
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
        // ex) localhost:8080/hello-mvc?name="spring123"

        model.addAttribute("name", name);

        // resources/templates/[return String]와 연결
        return "hello-template";
    }
}

```

#### View

```html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
  <head>
    <title>Hello</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
  </head>

  <body>
    <p th:text="'hello-mvc' + ${name}">Empty..?</p>
  </body>
</html>
```

#### MVC, 템플릿 엔진 처리 과정 (강의자료 이미지 참고)

1. 웹 브라우저에서 `localhost:8080/hello-mvc` 요청을 한다.
2. 해당 요청은 내장 tomcat server에게 먼저 전달된다.
3. 내장 tomcat server는 `hello-mvc`라는 요청이 왔다고 spring에게 던진다.
4. spring은 **HelloController**에 매핑된 정보를 찾다가, `@GetMapping("hello-mvc")`와 매핑된 `public String helloMvc()`를 발견하고, 해당 메서드를 호출한다.
5. 해당 메서드는 `hello-template` 이름을 return하고, model에는 `(name: spring123)`에 대한 key-value 값을 전달한다. 이제 **viewResolver**라는 화면과 관련된 어떤 해결자가 동작(view를 찾아주고 템플릿 엔진과 연결시켜준다고 이해)한다.
6. viewResolver는 `templates/` 경로 하위에서 앞서 return한 String 값(hello-template)과 똑같은 html 파일을 찾아 thymeleaf 템플릿 엔진에게 처리해달라고 넘긴다
7. 템플릿 엔진은 해당 파일을 렌더링하고, 변환한 HTML을 웹 브라우저에게 반환한다. (정적일 때는 HTML을 그냥 반환했음)
