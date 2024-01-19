package hello.hellospring.domain;

public class Member {
    private Long id; // 시스템이 채번한다
    private String name; // 이름

    // java bean 규약에 따른 getter, setter를 이용해 만들어준다
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
