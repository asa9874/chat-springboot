package com.example.domain.chatRoom.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.domain.chatRoom.domain.ChatRoom;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {
}