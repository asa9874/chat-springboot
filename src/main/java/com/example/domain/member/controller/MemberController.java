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
import com.example.domain.member.service.MemberService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/member")
public class MemberController {
    private final MemberService memberService;

    @GetMapping
    public ResponseEntity<List<MemberResponseDto>> getMembers() {
        List<MemberResponseDto> responseDtos = memberService.getMembers();
        return ResponseEntity.ok().body(responseDtos);
    }

    @GetMapping("/{memberId}")
    public ResponseEntity<MemberResponseDto> getMember(@PathVariable Long memberId) {
        MemberResponseDto responseDto = memberService.getMember(memberId);
        return ResponseEntity.ok().body(responseDto);
    }

    // TODO: 추후 JWT 인증객체로
    @GetMapping("/me")
    public ResponseEntity<MemberResponseDto> getMyInfo() {
        return ResponseEntity.ok().build();
    }

    @GetMapping("/me/chat-rooms")
    public ResponseEntity<List<Long>> getMyChatRoomIds() {
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{memberId}/chat-rooms")
    public ResponseEntity<List<Long>> getChatRoomIdsByMemberId(@PathVariable Long memberId) {
        List<Long> chatRoomIds = memberService.getChatRoomIdsByMemberId(memberId);
        return ResponseEntity.ok().body(chatRoomIds);
    }

    @PostMapping("register")
    public ResponseEntity<MemberResponseDto> register(@RequestBody MemberRegisterRequestDto requestDto) {
        MemberResponseDto responseDto = memberService.register(requestDto);
        return ResponseEntity.ok().body(responseDto);
    }

    @DeleteMapping("/{memberId}")
    public ResponseEntity<Void> deleteMember(@PathVariable Long memberId) {
        memberService.deleteMember(memberId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{memberId}")
    public ResponseEntity<MemberResponseDto> updateMember(@PathVariable Long memberId,
            @RequestBody MemberUpdateRequestDto requestDto) {
        MemberResponseDto responseDto = memberService.updateMember(memberId, requestDto);
        return ResponseEntity.ok().body(responseDto);
    }
}
