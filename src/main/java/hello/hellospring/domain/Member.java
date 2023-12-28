package hello.hellospring.domain;

// 회원 도메인 만들기(회원 객체)
public class Member {
    // 데이터: 회원 아이디, 회원 이름
    private Long id;
    private String name;

    public Long getId() {
        // id값 전달 (getter)
        return id;
    }

    public void setId(Long id) {
        // id값 세팅 (setter)
        this.id = id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
