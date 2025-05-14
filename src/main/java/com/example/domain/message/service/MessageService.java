package com.example.domain.message.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.domain.message.domain.Message;
import com.example.domain.message.dto.response.MessageResponseDto;
import com.example.domain.message.repository.MessageRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MessageService {
    private final MessageRepository messageRepository;

    public List<MessageResponseDto> getMessagesByChatRoomId(Long chatRoomId) {
        List<Message> messages = messageRepository.findByChatRoomId(chatRoomId);
        return messages.stream()
                .map(MessageResponseDto::from)
                .toList();
    }

    
}
