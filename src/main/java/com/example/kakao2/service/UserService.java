package com.example.kakao2.service;

import com.example.kakao2.exception.CustomException;
import com.example.kakao2.platform.Platform;
import com.example.kakao2.domain.Users;
import com.example.kakao2.dto.KakaoUserResponse;

import java.util.List;

public class UserService {

    // OAuth2LoginService 리스트를 주입받도록 설정
    private final List<OAuth2LoginService> oAuth2LoginServices;

    // 생성자를 통해 의존성 주입
    public UserService(List<OAuth2LoginService> oAuth2LoginServices) {
        this.oAuth2LoginServices = oAuth2LoginServices;
    }

    // loginByOAuth 메서드 추가
    public KakaoUserResponse.Login loginByOAuth(String code, Platform platform) {
        Users userEntity = null;

        // 적절한 OAuth2LoginService 찾기
        for (OAuth2LoginService oAuth2LoginService : oAuth2LoginServices) {
            if (oAuth2LoginService.supports().equals(platform)) {
                userEntity = oAuth2LoginService.toEntityUser(code, platform);
                break;
            }
        }

        // userEntity가 null인 경우 예외 발생
        if (userEntity == null) {
            throw new CustomException(CustomException.UNEXPECTED_EXCEPTION);
        }

        // UserResponse.Login 객체 생성 후 반환 (예제)
        return new KakaoUserResponse.Login(
                userEntity.getEmail(),               // 이메일
                userEntity.getName(),                // 이름
                userEntity.getProfileImageUrl()      // 프로필 이미지 URL
        );
    }
}