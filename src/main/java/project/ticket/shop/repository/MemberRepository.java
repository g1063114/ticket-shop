package project.ticket.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.ticket.shop.entity.Member;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member,Long> {

    List<Member> findByEmail(String email);
}
