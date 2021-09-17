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
        Member saveMember = memberRepository.save(member);
        return saveMember.getId();
    }

    public boolean duplicateCheck(String email){
        List<Member> findMember = memberRepository.findByEmail(email);
        if(!findMember.isEmpty()){
            return false;
        }else{
            return true;
        }
    }
}
