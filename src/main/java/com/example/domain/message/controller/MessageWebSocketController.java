package com.example.domain.message.controller;

import java.util.List;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import com.example.domain.chatRoom.service.ChatRoomService;
import com.example.domain.member.domain.Member;
import com.example.domain.message.dto.request.MessageCreateRequestDto;
import com.example.domain.message.dto.response.MessageResponseDto;
import com.example.domain.message.dto.response.MessageSocketResponseDto;
import com.example.domain.message.service.MessageService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MessageWebSocketController {
    private final SimpMessagingTemplate messagingTemplate;
    private final MessageService messageService;
    private final ChatRoomService chatRoomService;

    @MessageMapping("/chat/send")
    public void sendMessage(MessageCreateRequestDto requestDto, SimpMessageHeaderAccessor headerAccessor) {
        Long senderId = (Long) headerAccessor.getSessionAttributes().get("userId");
        MessageResponseDto savedMessage = messageService.sendMessage(requestDto, senderId);
        messagingTemplate.convertAndSend("/topic/chat/" + requestDto.getChatRoomId(), savedMessage);

        MessageSocketResponseDto responseDto = MessageSocketResponseDto.from(
                savedMessage, requestDto.getChatRoomId());
        // 1:1 알림 메시지도 전송
        List<Member> members = chatRoomService.findMembersByChatRoomId(requestDto.getChatRoomId());
        for (Member member : members) {
            if (!member.getId().equals(senderId)) {
                messagingTemplate.convertAndSendToUser(
                        member.getId().toString(),
                        "/queue/messages",
                        responseDto);
            }
        }
    }
}