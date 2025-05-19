package com.example.domain.member.service;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.domain.chatRoom.dto.response.ChatRoomResponseDto;
import com.example.domain.member.domain.Member;
import com.example.domain.member.dto.request.MemberRegisterRequestDto;
import com.example.domain.member.dto.request.MemberUpdateRequestDto;
import com.example.domain.member.dto.response.MemberResponseDto;
import com.example.domain.member.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public List<MemberResponseDto> getMembers() {
        List<Member> members = memberRepository.findAll();
        return members.stream()
                .map(MemberResponseDto::from)
                .toList();
    }

    public MemberResponseDto getMember(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("Member not found"));
        return MemberResponseDto.from(member);
    }

    public List<ChatRoomResponseDto> getChatRoomsByMemberId(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("Member not found"));
        return member.getChatRooms().stream()
                .map(ChatRoomResponseDto::from)
                .toList();
    }

    // TODO 추후 PassWordEncoder로 암호화
    public MemberResponseDto register(MemberRegisterRequestDto requestDto) {
        String encodedPassword = passwordEncoder.encode(requestDto.getPassword());
        Member member = Member.builder()
                .name(requestDto.getName())
                .email(requestDto.getEmail())
                .password(encodedPassword)
                .build();
        Member savedMember = memberRepository.save(member);
        return MemberResponseDto.from(savedMember);
    }

    public void deleteMember(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("Member not found"));
        memberRepository.delete(member);
    }

    public MemberResponseDto updateMember(Long memberId, MemberUpdateRequestDto requestDto) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("Member not found"));
        member.setName(requestDto.getName());
        Member updatedMember = memberRepository.save(member);
        return MemberResponseDto.from(updatedMember);
    }

    

}
