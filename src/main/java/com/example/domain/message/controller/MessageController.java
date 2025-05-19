package com.example.domain.message.controller;

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

import com.example.domain.message.dto.request.MessageCreateRequestDto;
import com.example.domain.message.dto.request.MessageUpdateRequestDto;
import com.example.domain.message.dto.response.MessageResponseDto;
import com.example.domain.message.service.MessageService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/message")
@RequiredArgsConstructor
public class MessageController {
    private final MessageService messageService;

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<MessageResponseDto>> getMessages() {
        List<MessageResponseDto> messages = messageService.getMessages();
        return ResponseEntity.ok().body(messages);
    }

    @GetMapping("/{messageId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<MessageResponseDto> getMessage(@PathVariable Long messageId) {
        MessageResponseDto message = messageService.getMessage(messageId);
        return ResponseEntity.ok().body(message);
    }

    @PostMapping
    public ResponseEntity<MessageResponseDto> createMessage(@RequestBody MessageCreateRequestDto requestDto) {
        MessageResponseDto message = messageService.createMessage(requestDto);
        return ResponseEntity.status(201).body(message);
    }

    @DeleteMapping("/{messageId}")
    public ResponseEntity<Void> deleteMessage(@PathVariable Long messageId) {
        messageService.deleteMessage(messageId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{messageId}")
    public ResponseEntity<MessageResponseDto> updateMessage(@PathVariable Long messageId,
            @RequestBody MessageUpdateRequestDto requestDto) {
        messageService.updateMessage(messageId, requestDto);
        return ResponseEntity.ok().body(null);
    }
}
