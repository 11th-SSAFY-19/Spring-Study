package com.example.demo.domain.member.controller;

import com.example.demo.domain.member.entity.Member;
import com.example.demo.domain.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/members")
public class MemberController {
    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping
    public ResponseEntity<String> join(@RequestBody Member member) {
        // TODO: parameter를 JoinRequestDto로 수정하기
        memberService.join(member);
        return new ResponseEntity<>("ok", HttpStatus.OK);
    }

    @GetMapping("/{memberId}")
    public ResponseEntity<Member> findByMemberId(@PathVariable(name = "memberId") Long memberId) {
        Member member = memberService.findByMemberId(memberId);
        return new ResponseEntity<>(member, HttpStatus.OK);
    }
}
