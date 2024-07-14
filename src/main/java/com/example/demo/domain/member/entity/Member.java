package com.example.demo.domain.member.entity;

import com.example.demo.global.common.entity.BaseTimeEntity;
import com.example.demo.global.common.exception.BaseException;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Member")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long memberId;

    @Column(name = "login_id")
    private String loginId;

    @Column(name = "password")
    private String password;

    @Column(name = "nickname")
    private String name;

    @Column(name = "age")
    private Integer age;


    @Builder
    public Member(String loginId, String password, String nickname, Integer age) {
        this.loginId = loginId;
        this.password = password;
        this.name = nickname;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Member{" +
                "memberId=" + memberId +
                ", loginId='" + loginId + '\'' +
                ", password='" + password + '\'' +
                ", age=" + age +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}