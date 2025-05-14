package com.example.domain.chatRoom.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.domain.chatRoom.domain.ChatRoom;
import com.example.domain.chatRoom.dto.request.ChatRoomCreateRequestDto;
import com.example.domain.chatRoom.dto.request.ChatRoomUpdateRequestDto;
import com.example.domain.chatRoom.dto.response.ChatRoomResponseDto;
import com.example.domain.chatRoom.repository.ChatRoomRepository;
import com.example.domain.member.domain.Member;
import com.example.domain.member.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ChatRoomService {

    private final ChatRoomRepository chatRoomRepository;
    private final MemberRepository memberRepository;

    public List<ChatRoomResponseDto> getChatRooms() {
        List<ChatRoom> chatRooms = chatRoomRepository.findAll();
        return chatRooms.stream()
                .map(ChatRoomResponseDto::from)
                .toList();
    }

    public ChatRoomResponseDto getChatRoomById(Long chatRoomId) {
        ChatRoom chatRoom = chatRoomRepository.findById(chatRoomId)
                .orElseThrow(() -> new RuntimeException("채팅방없음"));
        return ChatRoomResponseDto.from(chatRoom);
    }

    public ChatRoomResponseDto createChatRoom(ChatRoomCreateRequestDto requestDto) {
        Member owner = memberRepository.findById(requestDto.getOwnerId())
                .orElseThrow(() -> new RuntimeException("회원없음"));

        ChatRoom chatRoom = ChatRoom.builder()
                .roomName(requestDto.getRoomName())
                .roomDescription(requestDto.getRoomDescription())
                .owner(owner)
                .build();

        ChatRoom savedChatRoom = chatRoomRepository.save(chatRoom);
        return ChatRoomResponseDto.from(savedChatRoom);
    }

    public ChatRoomResponseDto updateChatRoom(Long chatRoomId, ChatRoomUpdateRequestDto requestDto) {
        ChatRoom chatRoom = chatRoomRepository.findById(chatRoomId)
                .orElseThrow(() -> new RuntimeException("채팅방없음"));

        chatRoom.setRoomName(requestDto.getRoomName());
        chatRoom.setRoomDescription(requestDto.getRoomDescription());

        chatRoomRepository.save(chatRoom);
        return ChatRoomResponseDto.from(chatRoom);
    }

    public void deleteChatRoom(Long chatRoomId) {
        ChatRoom chatRoom = chatRoomRepository.findById(chatRoomId)
                .orElseThrow(() -> new RuntimeException("채팅방없음"));
        chatRoomRepository.delete(chatRoom);
    }

}
