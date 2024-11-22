package com.example.kakao.token;

import com.example.kakao.domain.KakaoProperties;
import com.example.kakao.dto.KakaoTokenResponse;
import com.example.kakao.dto.KakaoUserResponse;
import com.example.kakao.exception.CustomException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class AccessToken {

    private final RestTemplate restTemplate = new RestTemplate();

    public String toRequestAccessToken(String authorizationCode, KakaoProperties kakaoProperties) {
        // HttpHeaders 객체 생성
        HttpHeaders headers = new HttpHeaders();

        // 헤더 설정 (필요한 값들 설정)
        headers.setContentType(org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED);

        // MultiValueMap으로 파라미터 설정
        org.springframework.util.MultiValueMap<String, String> params = new org.springframework.util.LinkedMultiValueMap<>();
        params.add("grant_type", kakaoProperties.getGrantType());
        params.add("client_id", kakaoProperties.getClientId());
        params.add("redirect_uri", kakaoProperties.getRedirectUri());
        params.add("code", authorizationCode);

        // HttpEntity 객체로 헤더와 파라미터 묶기
        HttpEntity<org.springframework.util.MultiValueMap<String, String>> request = new HttpEntity<>(params, headers);

        // API 호출
        ResponseEntity<KakaoTokenResponse> response = restTemplate.exchange(
                kakaoProperties.getTokenUri(),
                HttpMethod.POST,
                request,
                KakaoTokenResponse.class
        );

        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new CustomException("Token request failed");
        }

        // 응답에서 AccessToken 반환
        return response.getBody().getAccessToken();
    }

    public KakaoUserResponse toRequestProfile(String accessToken, KakaoProperties kakaoProperties) {
        // HttpHeaders 객체 생성
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken);  // Bearer Token 설정
        headers.setContentType(org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED);

        // HttpEntity 생성 (헤더만 전달)
        HttpEntity<String> entity = new HttpEntity<>(headers);

        // API 호출
        ResponseEntity<KakaoUserResponse> response = restTemplate.exchange(
                kakaoProperties.getUserInfoUri(),
                HttpMethod.GET,
                entity,
                KakaoUserResponse.class
        );

        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new CustomException("Profile request failed");
        }

        // 응답에서 사용자 정보 반환
        return response.getBody();
    }
}
