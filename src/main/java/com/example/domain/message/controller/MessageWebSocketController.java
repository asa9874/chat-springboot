package com.example.domain.message.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import com.example.domain.message.dto.request.MessageCreateRequestDto;
import com.example.domain.message.dto.response.MessageResponseDto;
import com.example.domain.message.service.MessageService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MessageWebSocketController {
    private final SimpMessagingTemplate messagingTemplate;
    private final MessageService messageService;

    @MessageMapping("/chat/send") 
    public void sendMessage(MessageCreateRequestDto requestDto, SimpMessageHeaderAccessor headerAccessor) {
        Long senderId = (Long) headerAccessor.getSessionAttributes().get("userId");
        MessageResponseDto savedMessage = messageService.sendMessage(requestDto,senderId);
        messagingTemplate.convertAndSend("/topic/chat/" + requestDto.getChatRoomId(), savedMessage);
    }
}
