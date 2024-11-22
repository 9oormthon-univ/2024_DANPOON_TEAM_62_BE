package com.example.kakao2.controller;

import com.example.kakao2.service.KakaoAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class KakaoAccountController {

    @Autowired
    private KakaoAccountService kakaoAccountService;

    @GetMapping("/deleteAccount")
    public String deleteAccount(String accessToken) {
        // 카카오 계정 연동 해제 요청
        kakaoAccountService.deleteAccount(accessToken);

        // 계정 연동 해제 후 홈 페이지로 리다이렉트
        return "redirect:/";
    }
}
