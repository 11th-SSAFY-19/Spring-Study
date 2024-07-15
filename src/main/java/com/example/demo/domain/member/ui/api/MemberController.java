package com.example.demo.domain.member.ui.api;


import com.example.demo.domain.member.ui.dto.MemberRequest;
import com.example.demo.domain.member.ui.dto.MemberResponse;
import com.example.demo.domain.member.entity.Member;
import com.example.demo.domain.member.service.MemberService;
import com.example.demo.global.common.response.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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