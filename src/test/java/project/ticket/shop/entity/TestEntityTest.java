package project.ticket.shop.entity;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class TestEntityTest {

    @Autowired
    EntityManager em;

    @Test
    public void Init(){
        TestEntity test = new TestEntity();
        em.persist(test);

        JPAQueryFactory queryFactory = new JPAQueryFactory(em);

        QTestEntity qTestEntity = QTestEntity.testEntity;

        TestEntity result = queryFactory
                .selectFrom(qTestEntity)
                .fetchOne();

        Assertions.assertThat(result).isEqualTo(test);
    }
}