package com.example.demo.domain.member.ui.dto;

import com.example.demo.domain.member.entity.Member;
import lombok.Builder;
import lombok.Getter;

public class MemberResponse {
    @Getter
    public static class MemberDto {
        private final Long memberId;
        private final String loginId;
        private final String nickname;
        private final Integer age;

        @Builder
        public MemberDto(Long memberId, String loginId, String nickname, Integer age) {
            this.memberId = memberId;
            this.loginId = loginId;
            this.nickname = nickname;
            this.age = age;
        }
    }
}
