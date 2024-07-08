package com.example.demo.domain.member.dto;

import com.example.demo.domain.member.entity.Member;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AddMemberRequest {

    private String loginId;
    private String password;
    private String name;
    private Integer age;

    public Member toEntity() {
        return Member.builder()
                .loginId(loginId)
                .password(password)
                .nickname(name)
                .age(age)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
    }
}
