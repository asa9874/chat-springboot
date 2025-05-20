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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@Tag(name = "메시지API", description = "/message")
@RequiredArgsConstructor
@RequestMapping("/api/message")
public class MessageController {
    private final MessageService messageService;

    @GetMapping
    @Operation(summary = "전체 메시지 조회", description = "모든 메시지를 조회함(ADMIN)")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<MessageResponseDto>> getMessages() {
        List<MessageResponseDto> messages = messageService.getMessages();
        return ResponseEntity.ok().body(messages);
    }

    @GetMapping("/{messageId}")
    @Operation(summary = "특정 메시지 조회", description = "특정 메시지를 조회함(ADMIN)")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<MessageResponseDto> getMessage(@PathVariable Long messageId) {
        MessageResponseDto message = messageService.getMessage(messageId);
        return ResponseEntity.ok().body(message);
    }

    @PostMapping
    @Operation(summary = "메시지 생성", description = "채팅방에 메시지를 생성함")
    public ResponseEntity<MessageResponseDto> createMessage(@RequestBody MessageCreateRequestDto requestDto) {
        MessageResponseDto message = messageService.createMessage(requestDto);
        return ResponseEntity.status(201).body(message);
    }


    @DeleteMapping("/{messageId}")
    @Operation(summary = "특정 메시지 삭제", description = "특정 메시지를 삭제함")
    public ResponseEntity<Void> deleteMessage(@PathVariable Long messageId) {
        messageService.deleteMessage(messageId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{messageId}")
    @Operation(summary = "특정 메시지 수정", description = "특정 메시지를 수정함")
    public ResponseEntity<MessageResponseDto> updateMessage(@PathVariable Long messageId,
            @RequestBody MessageUpdateRequestDto requestDto) {
        messageService.updateMessage(messageId, requestDto);
        return ResponseEntity.ok().body(null);
    }
}
