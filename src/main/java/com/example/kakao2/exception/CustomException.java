package com.example.kakao2.exception;

public class CustomException extends RuntimeException {
    public static final String UNEXPECTED_EXCEPTION = "Unexpected exception occurred";  // 메시지 상수 정의

    public CustomException(String message) {
        super(message);
    }
}


