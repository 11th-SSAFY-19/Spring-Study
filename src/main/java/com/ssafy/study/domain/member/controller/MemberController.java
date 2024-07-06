package com.ssafy.study.domain.member.controller;

import com.ssafy.study.domain.member.dto.MemberDto;
import com.ssafy.study.domain.member.entity.Member;
import com.ssafy.study.domain.member.service.MemberService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/members")
public class MemberController {
    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping
    public ResponseEntity<Member> createMember(@RequestBody MemberDto memberDto) {
        Member member = memberService.createMember(memberDto);
        return ResponseEntity.ok(member);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Member> getMemberById(@PathVariable Long id) {
        Member member = memberService.getMemberById(id);
        return ResponseEntity.ok(member);
    }
}
