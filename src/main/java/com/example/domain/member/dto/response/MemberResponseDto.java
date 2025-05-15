package com.example.domain.member.dto.response;

import java.util.List;

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
    public List<Long> chatRoomIds;

    public static MemberResponseDto from(Member member) {
        return MemberResponseDto.builder()
                .id(member.getId())
                .name(member.getName())
                .email(member.getEmail())
                .chatRoomIds(member.getChatRooms().stream()
                        .map(chatRoom -> chatRoom.getId())
                        .toList())
                .build();
    }
}
