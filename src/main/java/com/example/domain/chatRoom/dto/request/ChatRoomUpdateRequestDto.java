package com.example.domain.chatRoom.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChatRoomUpdateRequestDto {
    private String roomName;
    private String roomDescription;
    private Long[] memberIds;
}
