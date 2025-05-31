package com.example.domain.message.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MessageSocketResponseDto {
    private Long id;
    private String content;
    private Long senderId;
    private String senderName;
    private String timestamp;
    private Long chatRoomId;
    private String type;

    public static MessageSocketResponseDto from(MessageResponseDto message,Long chatRoomId) {
        return MessageSocketResponseDto.builder()
                .id(message.getId())
                .content(message.getContent())
                .senderId(message.getSenderId())
                .senderName(message.getSenderName())
                .timestamp(message.getTimestamp())
                .chatRoomId(chatRoomId)
                .type(message.getType())
                .build();
    }
}
