package com.viratech.supportdesk.mapper;

import com.viratech.supportdesk.domain.User;
import com.viratech.supportdesk.dto.UserRequest;
import com.viratech.supportdesk.dto.UserResponse;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User toEntity(UserRequest request){
        return User.builder()
                .name(request.name())
                .email(request.email())
                .build();
    }

    public UserResponse toDto(User user){
        return new UserResponse(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getRole(),
                user.getCreatedAt()
        );
    }
}
