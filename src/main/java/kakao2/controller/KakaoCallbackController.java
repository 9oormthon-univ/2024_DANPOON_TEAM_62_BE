package com.example.kakao2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class KakaoCallbackController {

    @GetMapping("/callback")
    public String callback(@RequestParam String code) {
        // 카카오 인증 코드로 토큰을 받아오는 로직 처리 후
        return "login-success"; // 로그인 성공 후 "login-success.html" 페이지로 리다이렉트
    }
}
