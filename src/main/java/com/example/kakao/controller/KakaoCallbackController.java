package com.example.kakao.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:3001") // 프론트엔드 CORS 허용
public class KakaoCallbackController {

    @Value("${kakao.client_id}")
    private String clientId;

    @Value("${kakao.redirect_uri}")
    private String redirectUri;

    @GetMapping("/callback")
    public ResponseEntity<Map<String, String>> callback(@RequestParam String code) {
        // 1. 액세스 토큰 요청
        String tokenUrl = "https://kauth.kakao.com/oauth/token";
        RestTemplate restTemplate = new RestTemplate();

        // 요청 데이터 URL 인코딩 형식으로 변경
        String requestBody = "grant_type=authorization_code" +
                "&client_id=" + clientId +
                "&redirect_uri=" + redirectUri +
                "&code=" + code;

        // 헤더 설정
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");

        // HttpEntity에 요청 본문과 헤더 추가
        HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);

        // 요청 보내기
        ResponseEntity<Map> tokenResponse = restTemplate.exchange(
                tokenUrl,
                HttpMethod.POST,
                entity,
                Map.class
        );

        String accessToken = (String) tokenResponse.getBody().get("access_token");

        // 2. 사용자 정보 요청
        String userInfoUrl = "https://kapi.kakao.com/v2/user/me";
        headers.clear();
        headers.set("Authorization", "Bearer " + accessToken); // accessToken을 헤더에 포함

        HttpEntity<String> userInfoEntity = new HttpEntity<>(headers);

        ResponseEntity<Map> userInfoResponse = restTemplate.exchange(
                userInfoUrl,
                HttpMethod.GET,
                userInfoEntity,
                Map.class
        );

        Map<String, Object> userInfo = userInfoResponse.getBody();

        // 3. 필요한 사용자 정보 추출
        Map<String, String> response = new HashMap<>();
        Map<String, Object> kakaoAccount = (Map<String, Object>) userInfo.get("kakao_account");

        // 이메일 추출 (이메일 동의 여부에 따라 값이 존재할 수 있음)
        String email = kakaoAccount.get("email") != null ? (String) kakaoAccount.get("email") : "";

        // 이름 추출 (닉네임 동의 여부에 따라 값이 존재할 수 있음)
        String name = "";
        if (kakaoAccount.get("profile") != null) {
            Map<String, Object> profile = (Map<String, Object>) kakaoAccount.get("profile");
            name = (String) profile.get("nickname");
        }

        // 프로필 이미지 URL 추출
        String profileImageUrl = "";
        if (kakaoAccount.get("profile") != null) {
            Map<String, Object> profile = (Map<String, Object>) kakaoAccount.get("profile");
            profileImageUrl = (String) profile.get("profile_image_url");
        }

        // 사용자 ID 추출 (userId)
        String userId = String.valueOf(userInfo.get("id"));

        // 추출한 정보 response에 담기
        response.put("userId", userId); // userId 추가
        response.put("email", email);
        response.put("name", name);
        response.put("profileImageUrl", profileImageUrl);

        // 헤더에 액세스 토큰 추가해서 반환
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Authorization", "Bearer " + accessToken); // 액세스 토큰을 헤더에 포함

        return new ResponseEntity<>(response, responseHeaders, HttpStatus.OK); // 액세스 토큰을 헤더에 포함한 응답 반환
    }
}
