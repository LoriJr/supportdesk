package com.viratech.supportdesk.service;

import com.viratech.supportdesk.domain.User;
import com.viratech.supportdesk.dto.UserRequest;
import com.viratech.supportdesk.dto.UserResponse;
import com.viratech.supportdesk.enums.Role;
import com.viratech.supportdesk.exceptions.InvalidParameterException;
import com.viratech.supportdesk.mapper.UserMapper;
import com.viratech.supportdesk.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.security.InvalidAlgorithmParameterException;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;
    private final UserMapper mapper;
    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    public UserResponse saveUser(UserRequest request){

        String className = UserService.class.getSimpleName();

        if(request.name() == null || request.name().isBlank()){
            throw new InvalidParameterException("Name must be not blank");
        }
        if(request.email() == null || request.email().isBlank()){
            throw new InvalidParameterException("Email must be not blank");
        }

        User user = mapper.toEntity(request);
        user.setRole(Role.EMPLOYEE);

        User savedUser = repository.save(user);

        log.info("[{}] [SaveUser] Recebido dados do usuário {}", className, savedUser.getId());

        return mapper.toDto(savedUser);
    }
}
