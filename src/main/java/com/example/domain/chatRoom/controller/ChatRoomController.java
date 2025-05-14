package com.example.domain.chatRoom.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.domain.chatRoom.dto.response.ChatRoomResponseDto;

@RestController
@RequestMapping("/api/chatroom")
public class ChatRoomController {

    @GetMapping
    public ResponseEntity<List<ChatRoomResponseDto>> getChatRooms() {
        return ResponseEntity.ok().build();
    }
    
}
