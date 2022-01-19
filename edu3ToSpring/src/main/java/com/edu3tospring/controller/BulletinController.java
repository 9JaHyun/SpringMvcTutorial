package com.edu3tospring.controller;

import com.edu3tospring.domain.bulletin.Bulletin;
import com.edu3tospring.domain.bulletin.BulletinService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/bulletins")
public class BulletinController {

    private final BulletinService bulletinService;

    @GetMapping("/addForm")
    public String addForm(Model model) {
        model.addAttribute("bulletin", new Bulletin());
        return "bulletin/form/addForm";
    }

    @PostMapping("/addForm")
    public String add(@RequestBody Bulletin bulletin) {
        bulletinService.save(bulletin);
        return "bulletin/bulletins";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Integer id, Model model) {
        Bulletin bulletin = bulletinService.findById(id);
        model.addAttribute("bulletin", bulletin);
        return "bulletin/form/editForm";

    }
}
