package com.example.domain.chatRoom.service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.domain.auth.util.AuthUtil;
import com.example.domain.chatRoom.domain.ChatRoom;
import com.example.domain.chatRoom.dto.request.ChatRoomCreateRequestDto;
import com.example.domain.chatRoom.dto.request.ChatRoomUpdateRequestDto;
import com.example.domain.chatRoom.dto.response.ChatRoomResponseDto;
import com.example.domain.chatRoom.repository.ChatRoomRepository;
import com.example.domain.member.domain.Member;
import com.example.domain.member.repository.MemberRepository;
import com.example.domain.message.dto.response.MessageResponseDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor
@Log4j2
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
        if (!AuthUtil.isAdminOrChatRoomMember(chatRoom)) {
            throw new RuntimeException("권한없음");
        }
        return ChatRoomResponseDto.from(chatRoom);
    }

    public List<MessageResponseDto> getMessagesByChatRoomId(Long chatRoomId) {
        ChatRoom chatRoom = chatRoomRepository.findById(chatRoomId)
                .orElseThrow(() -> new RuntimeException("채팅방없음"));
        if (!AuthUtil.isAdminOrChatRoomMember(chatRoom)) {
            throw new RuntimeException("권한없음");
        }
        return chatRoom.getMessages().stream()
                .map(MessageResponseDto::from)
                .toList();
    }

    public ChatRoomResponseDto createChatRoom(ChatRoomCreateRequestDto requestDto) {
        Member owner = memberRepository.findById(requestDto.getOwnerId())
                .orElseThrow(() -> new RuntimeException("회원없음"));
        Set<Member> members = requestDto.getMemberIds().stream()
                .map(memberId -> memberRepository.findById(memberId)
                        .orElseThrow(() -> new RuntimeException("회원없음")))
                .collect(Collectors.toSet());

        if(!AuthUtil.isAdmin() && !AuthUtil.isEqualMember(owner.getId())) {
            throw new RuntimeException("권한없음");
        }

        ChatRoom chatRoom = ChatRoom.builder()
                .roomName(requestDto.getRoomName())
                .roomDescription(requestDto.getRoomDescription())
                .owner(owner)
                .members(members)
                .build();

        ChatRoom savedChatRoom = chatRoomRepository.save(chatRoom);
        return ChatRoomResponseDto.from(savedChatRoom);
    }

    public ChatRoomResponseDto updateChatRoom(Long chatRoomId, ChatRoomUpdateRequestDto requestDto) {
        ChatRoom chatRoom = chatRoomRepository.findById(chatRoomId)
                .orElseThrow(() -> new RuntimeException("채팅방없음"));
        if (!AuthUtil.isAdminOrChatRoomOwner(chatRoom)) {
            throw new RuntimeException("권한없음");
        }
        chatRoom.setRoomName(requestDto.getRoomName());
        chatRoom.setRoomDescription(requestDto.getRoomDescription());

        chatRoomRepository.save(chatRoom);
        return ChatRoomResponseDto.from(chatRoom);
    }

    public void deleteChatRoom(Long chatRoomId) {
        ChatRoom chatRoom = chatRoomRepository.findById(chatRoomId)
                .orElseThrow(() -> new RuntimeException("채팅방없음"));
        if (!AuthUtil.isAdminOrChatRoomOwner(chatRoom)) {
            throw new RuntimeException("권한없음");
        }
        chatRoomRepository.delete(chatRoom);
    }

}
