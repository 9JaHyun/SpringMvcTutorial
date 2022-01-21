package com.edu3tospring.controller;

import com.edu3tospring.domain.notice.Notice;
import com.edu3tospring.domain.notice.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/notices")
public class NoticeController {

    private final NoticeService noticeService;

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("newNotice", new Notice());
        return "notice/form/addForm";
    }

    @PostMapping("/add")
    public String save(Notice notice) {
        noticeService.save(notice);
        return "redirect:/notices";
    }

    @GetMapping("/{id}")
    public String selectNotice(@PathVariable Integer id, Model model) {
        Notice notice = noticeService.findById(id);
        model.addAttribute("notice", notice);
        return "notice/noticeInfo";
    }

    @GetMapping("/{id}/edit")
    public String updateForm(@PathVariable Integer id, Model model) {
        Notice notice = noticeService.findById(id);
        model.addAttribute("notice", notice);
        return "notice/form/updateForm";
    }

    @PostMapping("/{id}/edit")
    public String updateNotice(@PathVariable Integer id, Notice notice) {
        noticeService.save(notice);
        return "redirect:/notices/{id}";
    }

    @PostMapping("/{id}/delete")
    public String deleteNotice(@PathVariable Integer id) {
        noticeService.deleteById(id);
        return "redirect:/notices";
    }
}
