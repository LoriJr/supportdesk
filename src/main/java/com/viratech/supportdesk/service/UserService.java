package com.viratech.supportdesk.service;

import com.viratech.supportdesk.domain.User;
import com.viratech.supportdesk.dto.UserRequest;
import com.viratech.supportdesk.dto.UserResponse;
import com.viratech.supportdesk.enums.Role;
import com.viratech.supportdesk.mapper.UserMapper;
import com.viratech.supportdesk.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;
    private final UserMapper mapper;
    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    public UserResponse saveUser(UserRequest request){

        String className = UserService.class.getSimpleName();

        if(request == null){
            throw new IllegalStateException("Request body must not be null");
        }

        User user = mapper.toEntity(request);
        user.setRole(Role.EMPLOYEE);
        user.setCreatedAt(LocalDateTime.now());

        User savedUser = repository.save(user);

        log.info("[{}] [SaveUser] Recebido dados do usuário {}", className, savedUser.getId());

        return mapper.toDto(savedUser);
    }
}
