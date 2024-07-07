package com.ssafy.study.domain.member.service;

import com.ssafy.study.domain.member.dto.MemberDto;
import com.ssafy.study.domain.member.entity.Member;
import com.ssafy.study.domain.member.repository.MemberRepository;
import com.ssafy.study.global.common.BaseException;
import com.ssafy.study.global.common.BaseResponseStatus;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class MemberService {
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Member createMember(MemberDto memberDto) {
        Member member = new Member(
                memberDto.getLoginId(),
                memberDto.getPassword(),
                memberDto.getNickname(),
                memberDto.getAge()
        );
        return memberRepository.save(member);
    }

    public Member getMemberById(Long id) {
        Optional<Member> member = memberRepository.findById(id);
        if (member.isPresent()) {
            return member.get();
        } else {
//            throw new BaseException(BaseResponseStatus.MEMBER_NOT_FOUND);
            throw new EntityNotFoundException("Member not found with id: " + id);
        }
    }
}
