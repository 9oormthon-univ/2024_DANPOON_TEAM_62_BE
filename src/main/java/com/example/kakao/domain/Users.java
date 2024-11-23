package com.example.kakao.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Users {
    private String email;
    private String name;
    private String profileImageUrl;

    // 필요에 따라 추가적인 필드나 메서드를 정의할 수 있습니다.
}
