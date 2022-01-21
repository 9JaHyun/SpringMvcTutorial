package com.edu3tospring.controller;

import com.edu3tospring.domain.reply.Reply;
import com.edu3tospring.domain.reply.ReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/reply")
public class ReplyController {

    private final ReplyService replyService;

    @PostMapping("/add")
    public void save(Reply reply) {
        replyService.save(reply);
    }
}
