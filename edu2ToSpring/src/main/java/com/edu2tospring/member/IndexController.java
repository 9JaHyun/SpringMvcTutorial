package com.edu2tospring.member;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class IndexController {
    private final MemberService memberService;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("members", memberService.findAll());
        return "index";
    }

    @GetMapping("/save")
    public String addForm() {
        return "members/addForm";
    }

    @GetMapping("/members/{id}")
    public String memberUpdate(@PathVariable Integer id, Model model) {
        Member member = memberService.findById(id);
        model.addAttribute("member", member);

        return "members/memberInfo/";
    }
}
