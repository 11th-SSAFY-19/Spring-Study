package com.example.demo.domain.member.service;

import com.example.demo.domain.member.dto.AddMemberRequest;
import com.example.demo.domain.member.entity.Member;
import com.example.demo.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public Member findById(long id) {
        return memberRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found memberId : " + id));
    }

    public Member save(AddMemberRequest request) {
        return memberRepository.save(request.toEntity());
    }
}
