package com.edu3tospring.controller;

import com.edu3tospring.domain.notice.NoticeJpa;
import com.edu3tospring.domain.notice.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/notice")
public class NoticeController {

    private final NoticeService noticeService;


    @GetMapping("/addForm")
    public String addForm(Model model) {

        model.addAttribute("notice", new NoticeJpa());
        return "notice/addForm";
    }
}
