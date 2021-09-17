package project.ticket.shop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.ticket.shop.entity.Member;
import project.ticket.shop.repository.MemberRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    /*
     * 회원 가입
     */
    @Transactional
    public Long join(Member member){
        int check = duplicateCheck(member);
        if (check != -1){
            memberRepository.save(member);
        }
        return member.getId();
    }

    private int duplicateCheck(Member member) {
        List<Member> findMember = memberRepository.findByEmail(member.getEmail());
        if(!findMember.isEmpty()){
            return -1;
        }else{
            return 0;
        }
    }
}
