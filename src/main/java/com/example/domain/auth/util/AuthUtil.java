package com.example.domain.auth.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.example.domain.chatRoom.domain.ChatRoom;
import com.example.global.jwt.CustomUserDetails;

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
        CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return chatRoom.getMembers().stream()
                .anyMatch(member -> member.getId().equals(userDetails.getId()));
    }

    public static boolean isChatRoomOwner(ChatRoom chatRoom) {
        return isEqualMember(chatRoom.getOwner().getId());
    }

    public static boolean isEqualMember(Long memberId) {
        CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userDetails.getId().equals(memberId);
    }
}