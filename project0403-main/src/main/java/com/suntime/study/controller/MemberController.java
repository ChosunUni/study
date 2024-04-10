package com.suntime.study.controller;

import com.suntime.study.dto.MemberDTO;
import com.suntime.study.service.MemberService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.BindingResult;

@Controller
public class MemberController {
    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/register")
    public String saveForm(Model model) {
        model.addAttribute("memberDTO", new MemberDTO()); // MemberDTO 객체를 모델에 추가
        return "register";
    }

    @PostMapping("/register")
    public String save(@Valid @ModelAttribute MemberDTO memberDTO, BindingResult result) {
        if (result.hasErrors()) {
            return "register"; // 유효성 검사 에러가 있으면 다시 폼으로 돌려보냄
        }
        // 회원가입 로직 수행
        memberService.save(memberDTO);
        return "index";
    }

    @GetMapping("/index1")
    public String loginForm() {
        return "index";
    }

    @PostMapping("/index")
    public String login(@ModelAttribute MemberDTO memberDTO, HttpSession session) {
        MemberDTO loginResult = memberService.login(memberDTO);
        if (loginResult != null) {
            session.setAttribute("loginEmail", loginResult.getMemberEmail());
            return "redirect:/timer"; // 로그인 성공 시 타이머 페이지로 이동
        } else {
            return "index"; // 로그인 실패 시 인덱스 페이지로 이동
        }
    }
}

