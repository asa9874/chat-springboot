package com.example.domain.message.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.domain.message.domain.Message;

public interface MessageRepository extends JpaRepository<Message, Long> {
}
