package com.example.domain.auth.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.example.domain.chatRoom.domain.ChatRoom;
import com.example.domain.member.domain.Member;

@Component
public class AuthUtil {
    public static boolean isAdminOrChatRoomMember(ChatRoom chatRoom) {
        return isAdmin() || isChatRoomMember(chatRoom);
    }

    public static boolean isAdminOrChatRoomOwner(ChatRoom chatRoom) {
        return isAdmin() || isChatRoomOwner(chatRoom);
    }

    public static boolean isAdmin() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || authentication.getAuthorities() == null) {
            return false;
        }

        return authentication.getAuthorities().stream()
                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_ADMIN"));
    }

    public static boolean isChatRoomMember(ChatRoom chatRoom) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || authentication.getPrincipal() == null) {
            return false;
        }

        Member member = (Member) authentication.getPrincipal();
        return chatRoom.getMembers().stream()
                .anyMatch(chatRoomMember -> chatRoomMember.getId().equals(member.getId()));
    }

    public static boolean isChatRoomOwner(ChatRoom chatRoom) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || authentication.getPrincipal() == null) {
            return false;
        }

        Member member = (Member) authentication.getPrincipal();
        return chatRoom.getOwner().getId().equals(member.getId());
    }

    public static boolean isEqualMember(Long memberId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || authentication.getPrincipal() == null) {
            return false;
        }

        Member authenticatedMember = (Member) authentication.getPrincipal();
        return authenticatedMember.getId().equals(memberId);
    }
}