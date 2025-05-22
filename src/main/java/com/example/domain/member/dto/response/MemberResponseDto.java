package com.example.domain.member.dto.response;

import com.example.domain.member.domain.Member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberResponseDto {
    private Long id;
    private String name;
    private String email;
    private String profileImageUrl;
    private String profileMessage;

    public static MemberResponseDto from(Member member) {
        return MemberResponseDto.builder()
                .id(member.getId())
                .name(member.getName())
                .email(member.getEmail())
                .profileImageUrl(member.getProfileImage())
                .profileMessage(member.getProfileMessage())
                .build();
    }
}
