package com.example.domain.member.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.domain.member.dto.request.MemberRegisterRequestDto;
import com.example.domain.member.dto.request.MemberUpdateRequestDto;
import com.example.domain.member.dto.response.MemberResponseDto;

@RestController
@RequestMapping("/api/member")
public class MemberController {

    @GetMapping
    public ResponseEntity<List<MemberResponseDto>> getMembers() {
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{memberId}")
    public ResponseEntity<MemberResponseDto> getMember(@PathVariable Long memberId) {
        return ResponseEntity.ok().build();
    }

    @GetMapping("/me")
    public ResponseEntity<MemberResponseDto> getMyInfo() {
        return ResponseEntity.ok().build();
    }

    @GetMapping("/me/chat-rooms")
    public ResponseEntity<List<Long>> getMyChatRoomIds() {
        return ResponseEntity.ok().build();
    }

    @PostMapping("register")
    public ResponseEntity<MemberResponseDto> register(@RequestBody MemberRegisterRequestDto requestDto) {
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{memberId}")
    public ResponseEntity<Void> deleteMember(@PathVariable Long memberId) {
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{memberId}")
    public ResponseEntity<MemberResponseDto> updateMember(@PathVariable Long memberId,
            @RequestBody MemberUpdateRequestDto requestDto) {
        return ResponseEntity.ok().build();
    }
}
