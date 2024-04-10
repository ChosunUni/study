package com.suntime.study.controller;

import com.suntime.study.entity.ToDoEntity;
import com.suntime.study.service.ToDoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class ToDoController {

    private final ToDoService toDoService;

    @GetMapping("/todo")
    public String list(Model model) {
        List<ToDoEntity> toDoEntityList = this.toDoService.getList();
        model.addAttribute("toDoEntityList", toDoEntityList);
        return "todolist";
    }


    // url 설정은 /todo/create
    @PostMapping("/todo/create")
    public String todoCreate(@RequestParam String content) {
        this.toDoService.create(content);
        // 다시 원래 화면으로 리다이렉트
        return "redirect:/todo";
    }
}