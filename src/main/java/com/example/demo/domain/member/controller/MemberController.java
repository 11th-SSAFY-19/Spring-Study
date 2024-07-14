package com.example.demo.domain.member.controller;


import com.example.demo.domain.member.dto.AddMemberRequest;
import com.example.demo.domain.member.dto.MemberResponse;
import com.example.demo.domain.member.entity.Member;
import com.example.demo.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/member")
public class MemberController {

    private final MemberService memberService;

    @PostMapping
    public ResponseEntity<Member> addMember(@RequestBody AddMemberRequest request) {
        Member savedMember = memberService.save(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedMember);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MemberResponse> findMember(@PathVariable long id) {
        Member member = memberService.findById(id);

        return ResponseEntity.ok()
                .body(new MemberResponse(member));
    }

}
