package com.example.domain.member.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.example.domain.chatRoom.domain.ChatRoom;
import com.example.domain.message.domain.Message;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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

    @Enumerated(EnumType.STRING)
    @Builder.Default
    private Role role = Role.USER;


    //이거 친구 단방향으로 설정함 그냥 추가하면 나만 친구로 추가되게 like 카카오톡
    @ManyToMany
    @Builder.Default
    private List<Member> friends = new ArrayList<>();



    public enum Role {
        USER, ADMIN
    }
}
