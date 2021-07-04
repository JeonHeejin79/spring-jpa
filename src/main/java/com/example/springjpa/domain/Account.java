package com.example.springjpa.domain;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

// @Entity : 자동적으로 테이블 이름과 매핑이 된다.
// @Entity(name="myAccount") 지정하는경우 지정 명으로 테이블이 생성되네
// @Table(name="calssname") 지정하는경우 해당 테이블명으로 테이블일 생성된다.
//
// primitive type long, reference type Long
// reference type : 을 주로 사용, 생성안할시에 기본값은 null
// primitive type : 세팅이 안될때 기본값 0 으로 세팅
@Entity
public class Account {

    // @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @GeneratedValue
    private Long id;
    // @Column(nullable = false, unique = true)
    private String username;
    private String password;

    // @OneToMany // 단방향 : 한 account 는 여러 스터디를 만들 수 있다.
    @OneToMany(mappedBy = "owner") // 양방향 : 한 account 는 여러 스터디를 만들 수 있다.
    private Set<Study> studies = new HashSet<>();

    //@Temporal(TemporalType.TIME) 시간으로만들어감
    /*
    @Temporal(TemporalType.TIMESTAMP)
    private Date created = new Date();
    */

    /*
    @Transient // 컬럼으로 매핑제외해준다.
    private String yes;
    @Transient // 컬럼으로 매핑제외해준다.
    private String no;
    */

    /*
    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name="street", column = @Column(name = "home_street"))
    })*/

    /*
    @Embedded
    private Address homeAddress;

    @Embedded
    private Address officeAddress;

    @Embedded
    private Address address;
    */

    public Set<Study> getStudies() {
        return studies;
    }

    public void setStudies(Set<Study> studies) {
        this.studies = studies;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // convenient method > add
    public void addStudy(Study study) {
        this.getStudies().add(study);
        study.setOwner(this); // this : Account
    }

    // convenient method > remove
    public void removeStudy(Study study) {
        this.getStudies().remove(study);
        study.setOwner(null);
    }
}
