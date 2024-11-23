package com.example.kakao.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class KakaoLogoutController {

    @CrossOrigin(origins = "http://localhost:3001")  // CORS 설정 추가
    @GetMapping("/logout")
    public void logout(@RequestHeader("Authorization") String authorizationHeader) {
        // 카카오 로그아웃 API 호출 URL
        String logoutUrl = "https://kapi.kakao.com/v1/user/logout";
        RestTemplate restTemplate = new RestTemplate();

        // 요청 헤더 설정
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/x-www-form-urlencoded");
        headers.set("Authorization", authorizationHeader); // Authorization 헤더에 액세스 토큰 포함

        // HttpEntity에 헤더 추가
        HttpEntity<String> entity = new HttpEntity<>(headers);

        // 로그아웃 API 호출
        restTemplate.exchange(
                logoutUrl,
                HttpMethod.POST,
                entity,
                String.class
        );
    }
}
