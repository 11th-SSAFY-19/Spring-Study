package com.ssafy.study.domain.member.ui.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

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
