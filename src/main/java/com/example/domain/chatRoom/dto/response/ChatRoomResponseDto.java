package com.example.domain.chatRoom.dto.response;

import com.example.domain.chatRoom.domain.ChatRoom;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class ChatRoomResponseDto {
    private Long id;
    private String roomName;
    private String roomDescription;
    private Long memberCount;
    private Long messageCount;
    private String lastMessage;
    private String lastMessageTime;
    private String lastMessageSender;
    private Long ownerId;
    private String ownerName;



    public static ChatRoomResponseDto from(ChatRoom chatRoom) {
        return ChatRoomResponseDto.builder()
                .id(chatRoom.getId())
                .roomName(chatRoom.getRoomName())
                .roomDescription(chatRoom.getRoomDescription())
                .memberCount((long) chatRoom.getMembers().size())
                .messageCount((long) chatRoom.getMessages().size())
                .lastMessage(chatRoom.getMessages().isEmpty() ? null : chatRoom.getMessages().iterator().next().getContent())
                .lastMessageTime(chatRoom.getMessages().isEmpty() ? null : chatRoom.getMessages().iterator().next().getTimestamp().toString())
                .lastMessageSender(chatRoom.getMessages().isEmpty() ? null : chatRoom.getMessages().iterator().next().getSender().getName())
                .ownerName(chatRoom.getOwner().getName())
                .ownerId(chatRoom.getOwner().getId())
                .build();
    }
}
