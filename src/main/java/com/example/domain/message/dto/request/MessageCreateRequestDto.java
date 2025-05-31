package com.example.domain.message.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MessageCreateRequestDto {
    private Long chatRoomId;
    private String content;
    private Long senderId;
    private String type;
}
