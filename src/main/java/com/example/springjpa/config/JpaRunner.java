package com.example.springjpa.config;

import com.example.springjpa.domain.Account;
import com.example.springjpa.domain.Study;
import org.hibernate.Session;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Component
@Transactional
public class JpaRunner implements ApplicationRunner {

    @PersistenceContext
    EntityManager entityManager; // 핵심 jpa api > hiberante 사용, hibernate api 사용가능

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Account account = new Account();
        account.setUsername("keesum");
        account.setPassword("hibernate1");

        Study study = new Study();
        study.setName("Spring Data JPA");

        // 관계형 설정
        // account.getStudies().add(study);
        account.addStudy(study);
        // study 가 관계의 주인이다.
        study.setOwner(account);


        Session session = entityManager.unwrap(Session.class);
        session.save(account);
        session.save(study);
        // entityManager.persist(account); // 영속화 : 저장


    }
}
