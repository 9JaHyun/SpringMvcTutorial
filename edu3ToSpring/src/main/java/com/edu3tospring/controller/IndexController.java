package com.edu3tospring.controller;

import com.edu3tospring.domain.bulletin.BulletinService;
import com.edu3tospring.domain.notice.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class IndexController {

    private final BulletinService bulletinService;
    private final NoticeService noticeService;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/bulletins")
    public String showBulletin(Model model) {
        model.addAttribute("bulletins", bulletinService.findAll());
        return "bulletin/bulletins";
    }

    @GetMapping("/notices")
    public String showNotices(Model model) {
        model.addAttribute("notices", noticeService.findAll());
        return "notice/notices";
    }

    @GetMapping("/test")
    public String test(Model model) {
        model.addAttribute("notices", noticeService.findAll());
        return "index1";
    }
}
