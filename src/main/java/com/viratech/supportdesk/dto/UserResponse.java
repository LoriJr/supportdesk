package com.viratech.supportdesk.dto;

import com.viratech.supportdesk.enums.Role;

import java.time.LocalDateTime;
import java.util.Set;

public record UserResponse(
        Long id,
        String name,
        String email,
        Role role,
        LocalDateTime createdAt
) {
}
