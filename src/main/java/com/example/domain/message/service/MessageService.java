package com.example.domain.message.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.domain.auth.util.AuthUtil;
import com.example.domain.chatRoom.domain.ChatRoom;
import com.example.domain.chatRoom.repository.ChatRoomRepository;
import com.example.domain.member.domain.Member;
import com.example.domain.member.repository.MemberRepository;
import com.example.domain.message.domain.Message;
import com.example.domain.message.dto.request.MessageCreateRequestDto;
import com.example.domain.message.dto.request.MessageUpdateRequestDto;
import com.example.domain.message.dto.response.MessageResponseDto;
import com.example.domain.message.repository.MessageRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MessageService {
    private final MessageRepository messageRepository;
    private final MemberRepository memberRepository;
    private final ChatRoomRepository chatRoomRepository;

    public List<MessageResponseDto> getMessages() {
        List<Message> messages = messageRepository.findAll();
        return messages.stream()
                .map(MessageResponseDto::from)
                .toList();
    }

    public MessageResponseDto getMessage(Long messageId) {
        Message message = messageRepository.findById(messageId)
                .orElseThrow(() -> new IllegalArgumentException("Message not found"));
        return MessageResponseDto.from(message);
    }

    public MessageResponseDto createMessage(MessageCreateRequestDto requestDto) {
        Member sender = memberRepository.findById(requestDto.getSenderId())
                .orElseThrow(() -> new IllegalArgumentException("Sender not found"));
        ChatRoom chatRoom = chatRoomRepository.findById(requestDto.getChatRoomId())
                .orElseThrow(() -> new IllegalArgumentException("Chat room not found"));

        if (!AuthUtil.isAdmin() && (!AuthUtil.isEqualMember(sender.getId()) || !AuthUtil.isChatRoomMember(chatRoom))) {
            throw new RuntimeException("권한없음");
        }

        Message message = Message.builder()
                .content(requestDto.getContent())
                .sender(sender)
                .chatRoom(chatRoom)
                .timestamp(LocalDateTime.now())
                .build();
        messageRepository.save(message);
        return MessageResponseDto.from(message);
    }

    public void deleteMessage(Long messageId) {
        Message message = messageRepository.findById(messageId)
                .orElseThrow(() -> new IllegalArgumentException("Message not found"));
        if (!AuthUtil.isAdmin() && !AuthUtil.isEqualMember(message.getSender().getId())) {
            throw new RuntimeException("권한없음");
        }
        messageRepository.delete(message);
    }

    public MessageResponseDto updateMessage(Long messageId, MessageUpdateRequestDto requestDto) {
        Message message = messageRepository.findById(messageId)
                .orElseThrow(() -> new IllegalArgumentException("Message not found"));
        if (!AuthUtil.isAdmin() && !AuthUtil.isEqualMember(message.getSender().getId())) {
            throw new RuntimeException("권한없음");
        }
        message.setContent(requestDto.getContent());
        messageRepository.save(message);
        return MessageResponseDto.from(message);
    }

}
