package com.edu2tospring.member;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class MemberService {
    private final MemberRepository memberRepository;

    public void save(Member member) {
        memberRepository.save(member);
    }

    public Member findById(Integer id) {
        return memberRepository.findById(id).orElseThrow(() -> new AssertionError("Fail to find Member! / memberId = " + id));
    }

    public List<Member> findAll() {
        return memberRepository.findAll();
    }

    public String update(Integer id, Member member) {
        Member findMember = memberRepository.findById(id).orElseThrow(() -> new AssertionError("Fail to find Member! / memberId = " + id));
        findMember.update(member);
        return member.getId();
    }

    public void deleteById(Integer id) {
        memberRepository.deleteById(id);
    }
}
