package com.ssafy.study.domain.member.ui.api;

import com.ssafy.study.domain.member.dao.MemberRepository;
import com.ssafy.study.domain.member.ui.dto.MemberRequest;
import com.ssafy.study.domain.member.entity.Member;
import com.ssafy.study.domain.member.service.MemberService;
import com.ssafy.study.domain.member.ui.dto.MemberResponse;
import com.ssafy.study.global.common.response.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @PostMapping
    public BaseResponse<Void> createMember(@RequestBody MemberRequest.MemberDto memberDto) {
        memberService.create(memberDto);
        return BaseResponse.successCreating();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MemberResponse.MemberDto> getMemberById(@PathVariable Long id) {
        MemberResponse.MemberDto memberDto = memberService.getMemberById(id);
        return ResponseEntity.ok(memberDto);
    }
}
