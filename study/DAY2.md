# DAY2

### 1. View 환경설정 - Welcome Page 만들기

#### 1-1) 정적 페이지 만들어보기

- [springboot의 index.html 관련 설명](https://docs.spring.io/spring-boot/docs/current/reference/html/web.html#web)

  - 스프링 부트는 정적 및 템플릿 시작 페이지를 지원한다.
  - 먼저 구성된 정적 콘텐츠 위치(static)에서 index.html 파일을 찾는다. 만약, 찾을 수 없으면 인덱스 템플릿(index.template)을 찾는다.
  - 둘 중 하나가 발견되면 자동으로 애플리케이션의 시작 페이지로 사용된다.

```
[resources/static/index.html]

<html lang="en">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <title>Document</title>
</head>
<body>
  Hello
  <a href="/hello">hello</a>
</body>
</html>
```

#### 1-2) thymeleaf 템플릿 엔진 사용하기

- thymeleaf 템플릿 엔진 (템플릿 엔진 : HTML을 정적으로 전달하는 것이 아니라, HTML을 서버에서 동적으로 바꿔서 전달하게끔 도와주는 도구)
- thymeleaf 공식 사이트: https://www.thymeleaf.org/
  - 스프링 공식 튜토리얼: https://spring.io/guides/gs/serving-web-content/
  - 스프링부트 메뉴얼: https://docs.spring.io/spring-boot/docs/2.3.1.RELEASE/reference/html/spring-boot-features.html#boot-features-spring-mvc-template-engines

> src/main/java/hello/hellosptring/controller/HelloControll.java

```
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
```

> src/main/resources/templates/hello.html

```
<!DOCTYPE html>
<!--th는 thymeleaf 템플릿 엔진이 선언된 부분이다. 스키마 선언-->
<html xmlns:th="http://www.thymeleaf.org">
  <head>
    <title>Hello</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
  </head>
  <body>
    <!--
 - thymeleaf 문법사용: p태그 안에 텍스트로서 "thymeleaf 테스트 중입니다. ' + ${data}" 내용이 들어감.
 - controller/HelloController.java에서 @GetMapping("hello") 한 부분에서, /hello 진입 시, public String hello(){} 함수가 실행되고,
   model(MVC)에 [key: "data", value:"hello spring!"]의 속성을 추가했다.
 - 그리고 해당 파일의 아래 부분에서는 key를 통해 value를 불러온 것이다.
-->
    <p th:text="'thymeleaf 테스트 중입니다. ' + ${data}"></p>
  </body>
</html>
```

> 해당 코드의 동작 과정을 이해하기 쉽게 설명하면 아래와 같다

````
1. 먼저 웹 브라우저에서 localhost:8080/hello URL로 진입한다.

2. Spring Boot는 Tomcat이라는 웹 서버(정확히는 WAS)를 내장하고 있는데, 이 내장 Tomcat서버가 경로 slot이 "/hello"임을 확인하고 spring한테 해당 경로에 대해 물어본다.

3. Spring은 helloController에서 @GetMapping("hello")부분을 통해, "/hello"부분에서 GET 요청이 이루어짐을 확인하고 매칭을 시켜준다. 그 결과로 public String hello() 메소드가 실행된다.

4. public String hello 메소드가 실행되면서, Spring은 argument로 model을 사용할 수 있도록, 모델을 만들어서 넘겨준다.
 그리고, hello메소드 내부에서는 넘겨받은 모델에 attributeName(key)와 attributeValue(value)라는 데이터를 추가한다.

5. 마지막으로 public String hello 메소드는 return "hello"를 수행하는데, 해당 return 문은 'resources/templates/hello.html' 파일을 연결시켜, 해당 파일을 렌더링 시켜 화면에 표시할 수 있도록 하는 구문이다.
 - 이 때, 화면을 찾아 처리하는 것은 'viewResolver'라는 것이 진행해준다.

6. 그렇게 Controller에서 return 값으로 문자를 반환하면 viewResolver는 렌더링할 화면을 찾게되는데, 이 때, Spring Boot에서 아래와 같은 방법으로 viewName이 매칭된다.
 - `resources:templates/` + {ViewName} + `.html`

7. 매칭된 파일의 상단에서, <html xmlns:th="http://www.thymeleaf.org">와 같이 스키마가 선언되어 있어야 템플릿 엔진 구문을 사용할 수 있다.

8. <body>태그 내부에서, <p th:text="'thymeleaf 테스트 중입니다. ' + ${data}"></p>와 같은 방법으로 넘겨받은 모델의 attributeName(key) 이름을 사용해 value값을 웹 브라우저에 출력 할 수 있다.
```원
````
