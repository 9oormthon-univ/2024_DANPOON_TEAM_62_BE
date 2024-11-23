package com.example.kakao.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Optional;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class KakaoUserResponse {

    private long id;

    @JsonProperty("has_signed_up")
    private boolean hasSignedUp;

    @JsonProperty("connected_at")
    private LocalDateTime connectedAt;

    private KakaoProperties properties;

    @JsonProperty("kakao_account")
    private KakaoAccount kakaoAccount;

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    public static class KakaoProperties {
        private String nickname;
    }
    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    public static class Login {
        private String email;
        private String name;
        private String profileImageUrl;
    }
    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    public static class KakaoAccount {
        @JsonProperty("profile_nickname_needs_agreement")
        private boolean profileNicknameNeedsAgreement;

        private KakaoProfile profile;

        @JsonProperty("has_email")
        private boolean hasEmail;

        @JsonProperty("email_needs_agreement")
        private boolean emailNeedsAgreement;

        @JsonProperty("is_email_valid")
        private boolean isEmailValid;

        @JsonProperty("is_email_verified")
        private boolean isEmailVerified;

        private String email;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    public static class KakaoProfile {
        private String nickname;
        @JsonProperty("profile_image_url")
        private String profileImageUrl;
    }

    // OAuth 클래스를 KakaoUserResponse 내부에 두는 경우
    @Getter
    @Builder
    public static class OAuth {
        private String email;
        private String name;
        private Optional<String> profileImageUrl;
    }
}
