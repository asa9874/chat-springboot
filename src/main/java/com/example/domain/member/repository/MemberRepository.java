package com.example.domain.member.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.domain.member.domain.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByEmail(String email);

}
