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
public class FriendInfoResponseDto {
    private Long id;
    private String name;
    private String profileImageUrl;
    private String profileMessage;


    public static FriendInfoResponseDto from(Member member) {
        return FriendInfoResponseDto.builder()
                .id(member.getId())
                .name(member.getName())
                .profileImageUrl(member.getProfileImage())
                .profileMessage(member.getProfileMessage())
                .build();
    }
}
