package com.example.kakao.service;

import com.example.kakao.domain.KakaoProperties;
import com.example.kakao.domain.Users;
import com.example.kakao.dto.KakaoTokenResponse;
import com.example.kakao.dto.KakaoUserResponse;
import com.example.kakao.exception.CustomException;
import com.example.kakao.platform.Platform;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class KakaoLoginService implements OAuth2LoginService {

    private final RestTemplate restTemplate = new RestTemplate();
    private final KakaoProperties kakaoProperties;

    @Override
    public Platform supports() {
        return Platform.KAKAO;
    }

    @Override
    public Users toEntityUser(String code, Platform platform) {
        String accessToken = toRequestAccessToken(code);
        KakaoUserResponse profile = toRequestProfile(accessToken);

        Users userEntity = new Users();
        userEntity.setEmail(profile.getKakaoAccount().getEmail());
        userEntity.setName(profile.getProperties().getNickname());
        userEntity.setProfileImageUrl(
                Optional.ofNullable(profile.getKakaoAccount().getProfile().getProfileImageUrl())
                        .orElse("defaultProfileImageUrl")
        );

        return userEntity;
    }

    @Override
    public KakaoUserResponse.OAuth toSocialEntityResponse(String code, Platform platform) {
        String accessToken = toRequestAccessToken(code);
        KakaoUserResponse profile = toRequestProfile(accessToken);

        return KakaoUserResponse.OAuth.builder()
                .email(profile.getKakaoAccount().getEmail())
                .name(profile.getProperties().getNickname())
                .profileImageUrl(
                        Optional.ofNullable(profile.getKakaoAccount().getProfile().getProfileImageUrl())
                )
                .build();
    }

    private String toRequestAccessToken(String authorizationCode) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", kakaoProperties.getGrantType());
        params.add("client_id", kakaoProperties.getClientId());
        params.add("redirect_uri", kakaoProperties.getRedirectUri());
        params.add("code", authorizationCode);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, headers);
        ResponseEntity<KakaoTokenResponse> response =
                restTemplate.postForEntity(kakaoProperties.getTokenUri(), request, KakaoTokenResponse.class);

        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new CustomException("FAIL");  // FAIL을 문자열로 수정
        }

        return response.getBody().getAccessToken();
    }

    private KakaoUserResponse toRequestProfile(String accessToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.setBearerAuth(accessToken);

        ResponseEntity<KakaoUserResponse> response = restTemplate.postForEntity(
                kakaoProperties.getUserInfoUri(), new HttpEntity<>(headers), KakaoUserResponse.class
        );

        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new CustomException("FAIL");  // FAIL을 문자열로 수정
        }

        return response.getBody();
    }

    private static final String KAKAO_LOGOUT_URL = "https://kapi.kakao.com/v1/user/logout";

    public void logout(String accessToken) {
        String logoutUrl = "https://kapi.kakao.com/v1/user/logout";

        // 요청 헤더에 액세스 토큰 추가
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + accessToken);

        // HttpEntity에 헤더 추가
        HttpEntity<String> logoutEntity = new HttpEntity<>(headers);

        // 로그아웃 요청 보내기
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(
                logoutUrl,
                HttpMethod.POST,
                logoutEntity,
                String.class
        );

        // 로그아웃 처리 결과 (여기서 응답은 필요에 따라 처리)
        if (response.getStatusCodeValue() == 200) {
            // 로그아웃 성공
            System.out.println("카카오 로그아웃 성공");
        } else {
            // 로그아웃 실패
            System.out.println("카카오 로그아웃 실패");
        }
    }
}

