package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class MemberService {
    //class에 커서 올린 후 ctrl+shift+t -> create test

    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
    회원 가입
     */
    public Long join(Member member){
        String validateMember = validateDuplicateMember(member); // 중복 회원 검즘
        System.out.println(validateMember);
        if(validateMember == null){
            memberRepository.save(member);
            return member.getId();
        }
        return -1L;
    }

    private String validateDuplicateMember(Member member) {
        Optional<Member> resultMember = memberRepository.findByName(member.getName());
        return resultMember.isEmpty() ? null : member.getName();
    }


    /**
     * 전체 회원 조회
     * @return
     */
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }

    public Optional<Member> findName(String name) { return memberRepository.findByName(name); }
}
