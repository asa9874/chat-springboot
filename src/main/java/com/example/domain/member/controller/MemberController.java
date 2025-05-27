package com.example.domain.member.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.domain.chatRoom.dto.response.ChatRoomResponseDto;
import com.example.domain.member.dto.request.MemberRegisterRequestDto;
import com.example.domain.member.dto.request.MemberUpdateRequestDto;
import com.example.domain.member.dto.response.FriendInfoResponseDto;
import com.example.domain.member.dto.response.MemberProfileResponseDto;
import com.example.domain.member.dto.response.MemberResponseDto;
import com.example.domain.member.service.MemberService;
import com.example.global.jwt.CustomUserDetails;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@Tag(name = "회원API", description = "/members")
@RequestMapping("/api/members")
public class MemberController {
    private final MemberService memberService;

    @GetMapping
    @Operation(summary = "맴버 전체 조회", description = "모든 멤버를 조회함(ADMIN)")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<MemberResponseDto>> getMembers() {
        List<MemberResponseDto> responseDtos = memberService.getMembers();
        return ResponseEntity.ok().body(responseDtos);
    }

    @GetMapping("/{memberId}")
    @Operation(summary = "특정 맴버 조회", description = "특정 맴버를 조회함")
    @PreAuthorize("hasRole('ROLE_ADMIN') or #memberId == authentication.principal.id")
    public ResponseEntity<MemberResponseDto> getMember(@PathVariable Long memberId) {
        MemberResponseDto responseDto = memberService.getMember(memberId);
        return ResponseEntity.ok().body(responseDto);
    }

    @GetMapping("/{memberId}/profile")
    @Operation(summary = "특정 맴버 프로필 조회", description = "특정 맴버의 프로필을 조회함(이건 인증 불필요함;)")
    public ResponseEntity<MemberProfileResponseDto> getMemberProfile(@PathVariable Long memberId) {
        MemberProfileResponseDto responseDto = memberService.getMemberProfile(memberId);
        return ResponseEntity.ok().body(responseDto);
    }

    @GetMapping("/me")
    @Operation(summary = "내 정보 조회", description = "JWT 기반으로 내정보 조회")
    public ResponseEntity<MemberResponseDto> getMyInfo(@AuthenticationPrincipal CustomUserDetails member) {
        MemberResponseDto responseDto = memberService.getMember(member.getId());
        return ResponseEntity.ok().body(responseDto);
    }

    @GetMapping("/me/friends")
    @Operation(summary = "내 친구들 조회", description = "JWT 기반으로 내 친구들 조회")
    public ResponseEntity<List<FriendInfoResponseDto>> getMyFriends(
            @AuthenticationPrincipal CustomUserDetails member) {
        List<FriendInfoResponseDto> responseDtos = memberService.getFriendsByMemberId(member.getId());
        return ResponseEntity.ok().body(responseDtos);
    }

    @GetMapping("/me/chatrooms")
    @Operation(summary = "내 채팅방들 조회", description = "JWT 기반으로 내가 들어가있는 채팅방들 조회")
    public ResponseEntity<List<ChatRoomResponseDto>> getMyChatRoomIds(
            @AuthenticationPrincipal CustomUserDetails member) {
        List<ChatRoomResponseDto> responseDto = memberService.getChatRoomsByMemberId(member.getId());
        return ResponseEntity.ok().body(responseDto);
    }

    @GetMapping("/{memberId}/chatrooms")
    @Operation(summary = "특정 맴버의 채팅방들 조회", description = "특정 맴버의 채팅방들 조회함")
    @PreAuthorize("hasRole('ROLE_ADMIN') or #memberId == authentication.principal.id")
    public ResponseEntity<List<ChatRoomResponseDto>> getChatRoomIdsByMemberId(@PathVariable Long memberId) {
        List<ChatRoomResponseDto> chatRoomIds = memberService.getChatRoomsByMemberId(memberId);
        return ResponseEntity.ok().body(chatRoomIds);
    }

    @GetMapping("/{memberId}/friends")
    @Operation(summary = "특정 맴버의 친구들 조회", description = "특정 맴버의 친구들 조회함")
    @PreAuthorize("hasRole('ROLE_ADMIN') or #memberId == authentication.principal.id")
    public ResponseEntity<List<FriendInfoResponseDto>> getFriendsByMemberId(@PathVariable Long memberId) {
        List<FriendInfoResponseDto> responseDtos = memberService.getFriendsByMemberId(memberId);
        return ResponseEntity.ok().body(responseDtos);
    }

    @PostMapping("/{memberId}/friends/{friendId}")
    @Operation(summary = "친구 추가", description = "친구를 추가함")
    @PreAuthorize("hasRole('ROLE_ADMIN') or #memberId == authentication.principal.id")
    public ResponseEntity<FriendInfoResponseDto> addFriend(@PathVariable Long memberId,
            @PathVariable Long friendId) {
        FriendInfoResponseDto responseDto = memberService.addFriend(memberId, friendId);
        return ResponseEntity.ok().body(responseDto);
    }

    @DeleteMapping("/{memberId}/friends/{friendId}")
    @Operation(summary = "친구 삭제", description = "친구를 삭제함")
    @PreAuthorize("hasRole('ROLE_ADMIN') or #memberId == authentication.principal.id")
    public ResponseEntity<Void> deleteFriend(@PathVariable Long memberId,
            @PathVariable Long friendId) {
        memberService.removeFriend(memberId, friendId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("register")
    @Operation(summary = "회원가입", description = "회원가입임(일반유저용)")
    public ResponseEntity<MemberResponseDto> register(@RequestBody MemberRegisterRequestDto requestDto) {
        MemberResponseDto responseDto = memberService.register(requestDto);
        return ResponseEntity.ok().body(responseDto);
    }

    @DeleteMapping("/{memberId}")
    @Operation(summary = "회원탈퇴", description = "회원을 삭제함")
    @PreAuthorize("hasRole('ROLE_ADMIN') or #memberId == authentication.principal.id")
    public ResponseEntity<Void> deleteMember(@PathVariable Long memberId) {
        memberService.deleteMember(memberId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{memberId}")
    @Operation(summary = "회원 정보수정", description = "일단 이름만 수정가능")
    @PreAuthorize("hasRole('ROLE_ADMIN') or #memberId == authentication.principal.id")
    public ResponseEntity<MemberResponseDto> updateMember(@PathVariable Long memberId,
            @RequestBody MemberUpdateRequestDto requestDto) {
        MemberResponseDto responseDto = memberService.updateMember(memberId, requestDto);
        return ResponseEntity.ok().body(responseDto);
    }
}
