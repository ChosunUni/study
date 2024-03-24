package com.suntime.study.controller;
import com.suntime.study.entity.TimerEntity;
import com.suntime.study.dto.TimerDTO;
import com.suntime.study.service.TimerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.RequiredArgsConstructor;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class TimerController {
    private final TimerService timerService;

    @PostMapping("/timer/subject")
    public String subject(@ModelAttribute TimerDTO timerDTO){
        // POST ??????? ?????? ???? ????? ???? ????????.
        System.err.println("timerDTO = " + timerDTO);
        timerService.save(timerDTO);
        return "redirect:/timer";
    }

    @GetMapping("/timer")
    public String subAllList(Model model) {
        List<TimerEntity> list = timerService.subAll();
        model.addAttribute("list", list);
        return "timer";
    }

}