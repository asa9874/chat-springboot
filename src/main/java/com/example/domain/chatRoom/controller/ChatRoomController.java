package com.example.domain.chatRoom.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.domain.chatRoom.dto.request.ChatRoomCreateRequestDto;
import com.example.domain.chatRoom.dto.request.ChatRoomUpdateRequestDto;
import com.example.domain.chatRoom.dto.response.ChatRoomResponseDto;
import com.example.domain.message.dto.response.MessageResponseDto;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/api/chatroom")
public class ChatRoomController {

    @GetMapping
    public ResponseEntity<List<ChatRoomResponseDto>> getChatRooms() {
        return ResponseEntity.ok().build();
    }

    //TODO: 이거아마 회원..?
    // @GetMapping("/{memberId}")
    // public ResponseEntity<List<ChatRoomResponseDto>> getChatRoomsByMemberId(@PathVariable Long memberId) {
    //     return ResponseEntity.ok().build();
    // }

    @GetMapping("/{chatRoomId}")
    public ResponseEntity<ChatRoomResponseDto> getChatRoomById(@PathVariable Long chatRoomId) {
        return ResponseEntity.ok().build();
    }

    @GetMapping("{chatRoomId}/messages")
    public ResponseEntity<List<MessageResponseDto>> getMessagesByChatRoomId(@PathVariable Long chatRoomId) {
        return ResponseEntity.ok().build();
    }

    @PostMapping
    public ResponseEntity<ChatRoomResponseDto> createChatRoom(@RequestBody ChatRoomCreateRequestDto requestDto) {
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{chatRoomId}")
    public ResponseEntity<Void> deleteChatRoom(@PathVariable Long chatRoomId) {
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{chatRoomId}")
    public ResponseEntity<ChatRoomResponseDto> updateChatRoom(@PathVariable Long chatRoomId,
            @RequestBody ChatRoomUpdateRequestDto requestDto) {
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{chatRoomId}/messages")
    public ResponseEntity<MessageResponseDto> sendMessage(@PathVariable Long chatRoomId) {
        return ResponseEntity.ok().build();
    }

}
