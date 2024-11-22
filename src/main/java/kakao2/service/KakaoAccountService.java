package com.example.kakao2.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

@Service
public class KakaoAccountService {

    private static final String KAKAO_ACCOUNT_DELETE_URL = "https://kapi.kakao.com/v1/user/unlink";

    // 카카오 계정 연동 해제 (회원 탈퇴와 유사한 동작)
    public void deleteAccount(String accessToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + accessToken);  // 카카오 API 접근을 위한 인증 토큰

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(KAKAO_ACCOUNT_DELETE_URL, HttpMethod.POST,
                new org.springframework.http.HttpEntity<>(headers), String.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            System.out.println("회원 탈퇴 성공");
        } else {
            System.out.println("회원 탈퇴 실패");
        }
    }
}
