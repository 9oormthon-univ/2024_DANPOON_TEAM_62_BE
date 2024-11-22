package com.example.kakao.service;


import com.example.kakao.domain.Users;
import com.example.kakao.dto.KakaoUserResponse;
import com.example.kakao.platform.Platform;

public interface OAuth2LoginService {
    Platform supports();

    // Platform과 code를 사용하여 UserEntity 객체를 반환하는 메서드
    Users toEntityUser(String code, Platform platform);

    KakaoUserResponse.OAuth toSocialEntityResponse(String code, Platform platform);
}
