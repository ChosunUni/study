package com.suntime.study.controller;

import com.suntime.study.entity.TimerEntity;
import com.suntime.study.dto.TimerDTO;
import com.suntime.study.service.TimerService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

import lombok.RequiredArgsConstructor;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class TimerController {
    private final TimerService timerService;

    @PostMapping("/timer/subject")
    public String subject(@ModelAttribute TimerDTO timerDTO) {
        // POST 요청에서 과목을 받아서 서비스를 통해 저장합니다.
        System.err.println("timerDTO = " + timerDTO);
        timerService.save(timerDTO);
        return "redirect:/timer";
    }

    @GetMapping("/timer")
    public String subAllList(Model model) {
        List<TimerEntity> list = timerService.subAll();
        model.addAttribute("list", list);
        return "/timer";
    }

    @PostMapping("/timer/del/{id}")
    public String delDataById(@PathVariable Long id) {
        timerService.delDataById(id);
        return "redirect:/timer"; // timer 페이지로 리다이렉트
    }

    @PostMapping("/save-time/{id}")
    public ResponseEntity<String> updateTimer(@PathVariable Long id, @RequestBody TimerDTO timerUpdateDTO) {
        try {
            timerService.updateTimer(id, timerUpdateDTO);
            return ResponseEntity.ok("Timer updated successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update timer");
        }
    }

    @GetMapping("/get-time/{id}")
    public ResponseEntity<TimerDTO> getTime(@PathVariable Long id) {
        TimerDTO timerDTO = timerService.getTime(id);
        return ResponseEntity.ok(timerDTO);
    }
}
