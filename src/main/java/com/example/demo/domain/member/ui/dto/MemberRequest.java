package com.example.demo.domain.member.ui.dto;

import com.example.demo.domain.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public class MemberRequest {
    @Getter
    public static class MemberDto {
        private final String loginId;
        private final String password;
        private final String nickname;
        private final Integer age;

        @Builder
        public MemberDto(String loginId, String password, String nickname, Integer age) {
            this.loginId = loginId;
            this.password = password;
            this.nickname = nickname;
            this.age = age;
        }
    }
}