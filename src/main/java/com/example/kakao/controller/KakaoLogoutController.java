package com.example.kakao.controller;

import com.example.kakao.service.KakaoLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class KakaoLogoutController {

    @Autowired
    private KakaoLoginService kakaoLoginService;

    @GetMapping("/logout")
    public String logout(String accessToken) {
        // 카카오 로그아웃 API 호출
        kakaoLoginService.logout(accessToken);

        // 세션 무효화 후 홈 페이지로 리다이렉트
        return "redirect:/";
    }
}
