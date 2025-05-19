package com.example.domain.member.domain;

import java.util.HashSet;
import java.util.Set;

import com.example.domain.chatRoom.domain.ChatRoom;
import com.example.domain.message.domain.Message;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
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
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String password;

    @ManyToMany(mappedBy = "members")
    @Builder.Default
    private Set<ChatRoom> chatRooms = new HashSet<>();

    @OneToMany(mappedBy = "sender")
    @Builder.Default
    private Set<Message> messages = new HashSet<>();
}