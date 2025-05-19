package com.example.domain.chatRoom.dto.request;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChatRoomCreateRequestDto {
    private String roomName;
    private String roomDescription;
    private Long ownerId;
    private List<Long> memberIds;
}
