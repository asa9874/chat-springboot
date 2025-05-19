package com.example.domain.auth.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.domain.auth.dto.request.LoginRequestDto;
import com.example.domain.auth.dto.response.LoginResponseDto;
import com.example.domain.member.domain.Member;
import com.example.domain.member.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public LoginResponseDto login(LoginRequestDto requestdDto) {
        Member member =memberRepository.findByEmail(requestdDto.getEmail())
                .orElseThrow(() -> new RuntimeException("Member not found"));

        if (!passwordEncoder.matches(requestdDto.getPassword(), member.getPassword())) {
            throw new RuntimeException("비밀번호가 일치하지 않습니다.");
        }

        //TODO: 토큰 생성로직
        String token = "추가해 ㅋ";


        return new LoginResponseDto(token);
    }
}
