package com.example.domain.message.domain;

import java.time.LocalDateTime;

import com.example.domain.chatRoom.domain.ChatRoom;
import com.example.domain.member.domain.Member;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "chatroom_id")
    private ChatRoom chatRoom;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member sender;

    private String content;
    private LocalDateTime timestamp;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    private Type type = Type.TEXT;

    public enum Type {
        TEXT, IMAGE, FILE
    }
}
