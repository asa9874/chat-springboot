package com.example.domain.message.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.domain.chatRoom.service.ChatRoomService;
import com.example.domain.member.domain.Member;
import com.example.domain.message.domain.Message.MessageType;
import com.example.domain.message.dto.request.MessageCreateRequestDto;
import com.example.domain.message.dto.request.MessageImageRequestDto;
import com.example.domain.message.dto.request.MessageUpdateRequestDto;
import com.example.domain.message.dto.response.MessageResponseDto;
import com.example.domain.message.dto.response.MessageSocketResponseDto;
import com.example.domain.message.service.ImageUploadService;
import com.example.domain.message.service.MessageService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@Tag(name = "메시지API", description = "/messages")
@RequiredArgsConstructor
@RequestMapping("/api/messages")
public class MessageController {
    private final MessageService messageService;
    private final ImageUploadService imageUploadService;
    private final SimpMessagingTemplate messagingTemplate;
    private final ChatRoomService chatRoomService;

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

    @PostMapping(value = "/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "이미지 업로드", description = "이미지를 업로드하고 URL을 반환함,브로드 캐스팅")
    public ResponseEntity<MessageResponseDto> uploadImage(
            @Valid @RequestPart("message") MessageImageRequestDto requestDto,
            @RequestParam("file") MultipartFile file) {
        MessageResponseDto savedMessage = messageService.createImageMessage(requestDto, file);

        //브로드 캐스팅
        messagingTemplate.convertAndSend(
                "/topic/chat/" + requestDto.getChatRoomId(),
                savedMessage);

        List<Member> members = chatRoomService.findMembersByChatRoomId(requestDto.getChatRoomId());
        MessageSocketResponseDto socketDto = MessageSocketResponseDto.from(savedMessage, requestDto.getChatRoomId());

        for (Member member : members) {
            if (!member.getId().equals(savedMessage.getSenderId())) {
                messagingTemplate.convertAndSendToUser(
                        member.getId().toString(),
                        "/queue/messages",
                        socketDto);
            }
        }

        return ResponseEntity.ok(savedMessage);
    }

}
