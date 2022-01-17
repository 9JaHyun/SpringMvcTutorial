package com.edu2tospring.member;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @PostMapping("/save")
    public String save(@ModelAttribute Member member) {
        memberService.save(member);
        return "redirect:/";
    }

    @PutMapping("/members/{id}")
    public String update(@PathVariable Integer id,
                       @RequestBody Member member) {
        memberService.update(id, member);
        return "redirect:/memberInfo/{id}";
    }

    @DeleteMapping("/members/{id}")
    public String delete(@PathVariable Integer id) {
        memberService.deleteById(id);
        return "redirect:/";
    }
}
