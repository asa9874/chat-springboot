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
    private String roomImage;



    public static ChatRoomResponseDto from(ChatRoom chatRoom) {
        String lastMessage = "";
        String lastMessageTime = "";
        String lastMessageSender = "";
        if(!chatRoom.getMessages().isEmpty()) {
            lastMessage = chatRoom.getMessages().get(chatRoom.getMessages().size() - 1).getContent();
            lastMessageTime = chatRoom.getMessages().get(chatRoom.getMessages().size() - 1).getTimestamp().toString();
            lastMessageSender = chatRoom.getMessages().get(chatRoom.getMessages().size() - 1).getSender().getName();
        }
        return ChatRoomResponseDto.builder()
                .id(chatRoom.getId())
                .roomName(chatRoom.getRoomName())
                .roomDescription(chatRoom.getRoomDescription())
                .memberCount((long) chatRoom.getMembers().size())
                .messageCount((long) chatRoom.getMessages().size())
                .lastMessage(lastMessage)
                .lastMessageTime(lastMessageTime)
                .lastMessageSender(lastMessageSender)
                .ownerName(chatRoom.getOwner().getName())
                .ownerId(chatRoom.getOwner().getId())
                .roomImage(chatRoom.getRoomImage())
                .build();
    }
}
