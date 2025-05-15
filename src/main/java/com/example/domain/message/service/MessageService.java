package com.example.domain.message.service;

import org.springframework.stereotype.Service;

import com.example.domain.message.repository.MessageRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MessageService {
    private final MessageRepository messageRepository;
    
}
