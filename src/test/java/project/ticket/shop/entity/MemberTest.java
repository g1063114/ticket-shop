package project.ticket.shop.entity;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import project.ticket.shop.repository.MemberRepository;
import project.ticket.shop.service.MemberService;

import javax.persistence.EntityManager;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberTest {

    @Autowired
    EntityManager em;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    MemberService memberService;

    @Test
    public void BaseEntityTest(){
        // given
        Member member = new Member("member1","g1063114@naver.com",28);
        memberRepository.save(member);
        em.persist(member);

        em.flush();
        em.clear();

        // when
        Member findMember = memberRepository.findById(member.getId()).get();

        // then
        System.out.println("findMember = " + findMember.getCreatedDate());
        System.out.println("findMember = " + findMember.getLastModifiedDate());
//        System.out.println("findMember = " + findMember);
//        System.out.println("findMember = " + findMember.getLastModifiedBy());
    }

    @Test
    public void list(){
        // given
        Member member1 = new Member("member1", "g1063114@naver.com", 28);
        Member member2 = new Member("member2", "abczz33@naver.com", 24);
        Member member3 = new Member("member3", "cjh5054@gmail.com", 20);
        Member member4 = new Member("member4", "g1063114@gmail.com", 22);

        em.persist(member1);
        em.persist(member2);
        em.persist(member3);
        em.persist(member4);

        em.flush();
        em.clear();

        // when
        List<Member> members = memberRepository.findAll();

        // then
        for (Member member : members) {
            System.out.println("member.username = " + member.getUsername());
        }

    }
}