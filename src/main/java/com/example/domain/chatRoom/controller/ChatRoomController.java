package com.example.domain.chatRoom.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.domain.chatRoom.dto.request.ChatRoomCreateRequestDto;
import com.example.domain.chatRoom.dto.request.ChatRoomUpdateRequestDto;
import com.example.domain.chatRoom.dto.response.ChatRoomResponseDto;
import com.example.domain.chatRoom.service.ChatRoomService;
import com.example.domain.message.dto.response.MessageResponseDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@RequiredArgsConstructor
@Log4j2
@Tag(name = "채팅방API", description = "/chatroom")
@RequestMapping("/api/chatroom")
public class ChatRoomController {

    private final ChatRoomService chatRoomService;

    @GetMapping
    @Operation(summary = "채팅방 리스트 반환", description = "모든채팅방을 리스트로 반환받음(ADMIN)")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<ChatRoomResponseDto>> getChatRooms() {
        List<ChatRoomResponseDto> chatRooms = chatRoomService.getChatRooms();
        return ResponseEntity.ok().body(chatRooms);
    }

    @GetMapping("/{chatRoomId}")
    @Operation(summary = "채팅방을 반환", description = "특정채팅방을 반환받음")
    public ResponseEntity<ChatRoomResponseDto> getChatRoomById(@PathVariable Long chatRoomId) {
        ChatRoomResponseDto chatRoom = chatRoomService.getChatRoomById(chatRoomId);
        return ResponseEntity.ok().body(chatRoom);
    }

    @GetMapping("{chatRoomId}/messages")
    @Operation(summary = "채팅방의 메시지 리스트 반환", description = "특정채팅방의 메시지 리스트 반환받음")
    public ResponseEntity<List<MessageResponseDto>> getMessagesByChatRoomId(@PathVariable Long chatRoomId) {
        List<MessageResponseDto> messages = chatRoomService.getMessagesByChatRoomId(chatRoomId);
        return ResponseEntity.ok().body(messages);
    }

    @PostMapping
    @Operation(summary = "채팅방 생성", description = "새로운 채팅방을 생성함")
    public ResponseEntity<ChatRoomResponseDto> createChatRoom(@RequestBody ChatRoomCreateRequestDto requestDto) {
        ChatRoomResponseDto chatRoom = chatRoomService.createChatRoom(requestDto);
        return ResponseEntity.status(201).body(chatRoom);
    }

    @DeleteMapping("/{chatRoomId}")
    @Operation(summary = "채팅방 지우기", description = "기존 채팅방을 지움")
    public ResponseEntity<Void> deleteChatRoom(@PathVariable Long chatRoomId) {
        chatRoomService.deleteChatRoom(chatRoomId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{chatRoomId}")
    @Operation(summary = "채팅방 업데이트", description = "기존 채팅방이름,설명,맴버를 수정함")
    public ResponseEntity<ChatRoomResponseDto> updateChatRoom(@PathVariable Long chatRoomId,
            @RequestBody ChatRoomUpdateRequestDto requestDto) {
        ChatRoomResponseDto updatedChatRoom = chatRoomService.updateChatRoom(chatRoomId, requestDto);
        return ResponseEntity.ok().body(updatedChatRoom);
    }

    // @PostMapping("/{chatRoomId}/messages")
    // public ResponseEntity<MessageResponseDto> sendMessage(@PathVariable Long
    // chatRoomId) {
    // return ResponseEntity.ok().build();
    // }

    // TODO: 이거아마 회원..?
    // @GetMapping("/{memberId}")
    // public ResponseEntity<List<ChatRoomResponseDto>>
    // getChatRoomsByMemberId(@PathVariable Long memberId) {
    // return ResponseEntity.ok().build();
    // }

}
