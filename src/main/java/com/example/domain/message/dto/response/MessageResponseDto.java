package com.example.domain.message.dto.response;

import com.example.domain.message.domain.Message;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MessageResponseDto {
    private Long id;
    private String content;
    private Long senderId;
    private String senderName;
    private String timestamp;

    public static MessageResponseDto from(Message message) {
        return MessageResponseDto.builder()
                .id(message.getId())
                .content(message.getContent())
                .senderId(message.getSender().getId())
                .senderName(message.getSender().getName())
                .timestamp(message.getTimestamp().toString())
                .build();
    }
}
