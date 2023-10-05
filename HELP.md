# 스프링 입문

왜 배우는가?

- 프론트엔드 개발을 진행하면서, 백엔드 측에서 데이터가 어떻게 넘어오는지에 대한 호기심과 함께, 실무에서 서로 간의 회의 과정에서 백엔드의 입장을 이해하기 위해 계획하였습니다.

# Install

### 1. Java

- 프로젝트 계획 당시의 Java SDK 최신버전(jdk-21)을 설치하였습니다.

### 2. Intellij or Vscode

- 실습을 진행할 텍스트 편집기를 설치하였습니다.

### 3. Spring Boot 압축파일 설치

- [설치 경로](https://start.spring.io)

  > Project: Gradle-Groovy

  > Language : Java

  > Spring Boot : snapshot이 아닌 정식 릴리즈된 버전 중 최신버전 선택

# STUDY

### 1. 라이브러리(2023-10-04)

> Gradle(그루비 기반 오픈소스로 공개된 빌드도구)은 의존관계가 있는 라이브러리를 함께 다운로드 한다.

> 스프링 부트 라이브러리

- spring-boot-starter-web

  - spring-boot-starter-tomcat: 톰캣 (웹서버)
  - spring-webmvc: 스프링 웹 MVC

- spring-boot-starter-thymeleaf: 타임리프 템플릿 엔진(View)

- spring-boot-starter(공통): 스프링 부트 + 스프링 코어 + 로깅
  - spring-boot
    - spring-core
  - spring-boot-starter-logging
    - logback, slf4j

> 테스트 라이브러리

- spring-boot-starter-test
  - junit: 테스트 프레임워크
  - mockito: 목 라이브러리
  - assertj: 테스트 코드를 좀 더 편하게 작성하게 도와주는 라이브러리
  - spring-test: 스프링 통합 테스트 지원

### 2. View 환경설정 - Welcome Page 만들기(2023-10-05)

#### 1. 정적 페이지 만들어보기

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

#### 2. thymeleaf 템플릿 엔진

- thymeleaf 템플릿 엔진
- thymeleaf 공식 사이트: https://www.thymeleaf.org/
  - 스프링 공식 튜토리얼: https://spring.io/guides/gs/serving-web-content/
  - 스프링부트 메뉴얼: https://docs.spring.io/spring-boot/docs/2.3.1.RELEASE/reference/html/spring-boot-features.html#boot-features-spring-mvc-template-engines
