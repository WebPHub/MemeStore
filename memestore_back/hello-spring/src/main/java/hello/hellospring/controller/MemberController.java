package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.domain.MyMessage;
import hello.hellospring.service.MemberService;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
public class MemberController {
    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @ResponseBody
    @PostMapping("/members/new")
    public Object create(@RequestBody MemberForm form){
        Member member = new Member();
        member.setName(form.getName());
        member.setPassword(form.getPassword());

        System.out.println("member = " + member.getName() + ", " + member.getPassword());

        if(memberService.join(member) != -1)
            return member;

        return new MyMessage("아이디가 중복되었습니다.");
    }

    @ResponseBody
    @PostMapping("/members/login")
    public Object login(@RequestBody MemberForm form){
        Member member = new Member();
        member.setName(form.getName());
        member.setPassword(form.getPassword());

        Member result = login(member);
        if(result != null){
            return result;
        }else {
            return new MyMessage("아이디와 비밀번호를 확인해주세요.");
        }
    }

    public Member login(Member member) {
        Optional<Member> findUser = memberService.findName(member.getName());

        if(findUser.isEmpty()){
            return null;
        }
        else if(!findUser.get().getPassword().equals(member.getPassword())){
            return null;
        }
        return findUser.get();
    }
}
