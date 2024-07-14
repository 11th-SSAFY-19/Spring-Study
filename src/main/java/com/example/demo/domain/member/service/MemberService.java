package com.example.demo.domain.member.service;

import com.example.demo.domain.member.service.converter.MemberConverter;
import com.example.demo.domain.member.ui.dto.MemberRequest;
import com.example.demo.domain.member.entity.Member;
import com.example.demo.domain.member.repository.MemberRepository;
import com.example.demo.domain.member.ui.dto.MemberResponse;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    @Transactional(readOnly = false)
    public void create(MemberRequest.MemberDto memberDto) {
        Member member = MemberConverter.toMember(memberDto);
        memberRepository.save(member);
    }

    @Transactional(readOnly = true)
    public MemberResponse.MemberDto getMemberById(Long id) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Member not found with id: " + id));
        return MemberConverter.toMemberDto(member);
    }
}
