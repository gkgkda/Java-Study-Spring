package hello.hellospring.domain;

import jakarta.persistence.*;

// JPA : ORM(Object Relational(DB) Mapping)

@Entity
public class Member {

    // PK(id) mapping   // DB에 값을 넣으면 자동으로 id를 생성 = Identity Strategy
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;    // 임의의 값

    @Column(name = "name")
    private String name;

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
