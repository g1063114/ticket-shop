package project.ticket.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.ticket.shop.entity.Member;

public interface MemberRepository extends JpaRepository<Member,Long> {

}
