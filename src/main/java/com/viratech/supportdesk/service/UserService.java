package com.viratech.supportdesk.service;

import com.viratech.supportdesk.domain.User;
import com.viratech.supportdesk.dto.UserRequest;
import com.viratech.supportdesk.dto.UserResponse;
import com.viratech.supportdesk.enums.Role;
import com.viratech.supportdesk.exceptions.EmailAlreadyExistsException;
import com.viratech.supportdesk.exceptions.InvalidParameterException;
import com.viratech.supportdesk.mapper.UserMapper;
import com.viratech.supportdesk.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.security.InvalidAlgorithmParameterException;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;
    private final UserMapper mapper;
    private static final Logger log = LoggerFactory.getLogger(UserService.class);
    private final String className = UserService.class.getSimpleName();

    public UserResponse saveUser(UserRequest request){

        if(validateEmailIsExists(request.email())){
            throw new EmailAlreadyExistsException("Email already exists.");
        }

        User user = mapper.toEntity(request);
        user.setRole(Role.EMPLOYEE);
        user.setCreatedAt(LocalDateTime.now());

        User savedUser = repository.save(user);

        log.info("[{}] [SaveUser] Recebido dados do usuário {}", className, savedUser.getId());

        return mapper.toDto(savedUser);
    }

    public boolean validateEmailIsExists(String email){
        return repository.findUserByEmail(email).isPresent();
    }
}
