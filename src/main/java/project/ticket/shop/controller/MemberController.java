package project.ticket.shop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import project.ticket.shop.dto.MemberForm;
import project.ticket.shop.entity.Member;
import project.ticket.shop.service.MemberService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/members/new")
    public String createMember(Model model){
        model.addAttribute("memberForm", new MemberForm());
        return "members/createMemberForm";
    }

    @ResponseBody
    @PostMapping("/members/check")
    public String checkEmail(@RequestParam("email") String email){
        String chk = null;
        boolean duplicateCheck = memberService.duplicateCheck(email);
        if(duplicateCheck){
            chk = "true";
            return chk;
        }else{
            chk = "false";
            return chk;
        }
    }

    @PostMapping("/members/new")
    public String saveMember(@Valid MemberForm form, BindingResult result){

        if(result.hasErrors()){
            return "members/createMemberForm";
        }

        Member member = new Member();
        member.setAge(form.getAge());
        member.setEmail(form.getEmail());
        member.setUsername(form.getUsername());

        memberService.join(member);
        return "redirect:/";
    }

    @GetMapping("/members")
    public String memberList(Model model){
        List<Member> members = memberService.memberList();
        model.addAttribute("members",members);

        return "members/memberList";
    }
}
