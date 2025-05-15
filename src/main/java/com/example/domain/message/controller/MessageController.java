package com.example.domain.message.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.domain.message.dto.request.MessageCreateRequestDto;
import com.example.domain.message.dto.request.MessageUpdateRequestDto;
import com.example.domain.message.dto.response.MessageResponseDto;

@RestController
@RequestMapping("/api/message")
public class MessageController {

    @GetMapping
    public ResponseEntity<List<MessageResponseDto>> getMessages() {
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{messageId}")
    public ResponseEntity<MessageResponseDto> getMessage(@PathVariable Long messageId) {
        return ResponseEntity.ok().build();
    }

    // TODO: MemberID는 추후 인증개체로
    @PostMapping
    public ResponseEntity<MessageResponseDto> createMessage(@RequestBody MessageCreateRequestDto requestDto) {
        return ResponseEntity.ok().build();
    }

    // TODO: MemberID는 추후 인증개체로
    @DeleteMapping("/{messageId}")
    public ResponseEntity<Void> deleteMessage() {
        return ResponseEntity.noContent().build();
    }

    // TODO: MemberID는 추후 인증개체로
    @PutMapping("/{messageId}")
    public ResponseEntity<MessageResponseDto> updateMessage(@RequestBody MessageUpdateRequestDto requestDto) {
        return ResponseEntity.ok().body(null);
    }
}
