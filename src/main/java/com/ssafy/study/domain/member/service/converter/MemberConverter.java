package com.ssafy.study.domain.member.service.converter;

import com.ssafy.study.domain.member.entity.Member;
import com.ssafy.study.domain.member.ui.dto.MemberRequest;
import com.ssafy.study.domain.member.ui.dto.MemberResponse;

public class MemberConverter {

    public static Member toMember(MemberRequest.MemberDto memberDto) {
        return Member.builder()
                .loginId(memberDto.getLoginId())
                .password(memberDto.getPassword())
                .nickname(memberDto.getNickname())
                .age(memberDto.getAge())
                .build();
    }

    public static MemberResponse.MemberDto toMemberDto(Member member) {
        return MemberResponse.MemberDto
                .builder()
                .memberId(member.getMemberId())
                .loginId(member.getLoginId())
                .age(member.getAge())
                .nickname(member.getName())
                .build();
    }
}
