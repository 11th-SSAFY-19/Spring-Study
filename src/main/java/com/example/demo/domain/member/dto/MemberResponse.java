package com.example.demo.domain.member.dto;

import com.example.demo.domain.member.entity.Member;
import lombok.Getter;

@Getter
public class MemberResponse {

    private final String name;
    private final String loginId;

    public MemberResponse(Member member) {
        this.name = member.getName();
        this.loginId = member.getLoginId();
    }

}
