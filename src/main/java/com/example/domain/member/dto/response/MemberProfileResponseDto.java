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
public class MemberProfileResponseDto {
    private Long id;
    private String name;
    private String profileImageUrl;
    private String profileMessage;

    public static MemberProfileResponseDto from(Member member) {
        return MemberProfileResponseDto.builder()
                .id(member.getId())
                .name(member.getName())
                .profileImageUrl(member.getProfileImage())
                .profileMessage(member.getProfileMessage())
                .build();
    }
}
