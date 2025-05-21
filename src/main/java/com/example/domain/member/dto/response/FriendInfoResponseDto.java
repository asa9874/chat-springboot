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

    public static FriendInfoResponseDto from(Member member) {
        return FriendInfoResponseDto.builder()
                .id(member.getId())
                .name(member.getName())
                .build();
    }
}
