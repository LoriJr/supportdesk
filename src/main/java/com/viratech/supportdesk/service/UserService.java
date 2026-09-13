package com.viratech.supportdesk.service;

import com.viratech.supportdesk.domain.User;
import com.viratech.supportdesk.dto.UserRequest;
import com.viratech.supportdesk.dto.UserResponse;
import com.viratech.supportdesk.enums.Role;
import com.viratech.supportdesk.exceptions.ConflictException;
import com.viratech.supportdesk.exceptions.InvalidParameterException;
import com.viratech.supportdesk.mapper.UserMapper;
import com.viratech.supportdesk.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;
    private final UserMapper mapper;
    private static final Logger log = LoggerFactory.getLogger(UserService.class);
    private final String className = UserService.class.getSimpleName();

    public UserResponse saveUser(UserRequest request){

        if (request.name() == null || request.name().isBlank()) {
            throw new InvalidParameterException("Name must be not blank");
        }
        if (request.email() == null || request.email().isBlank()) {
            throw new InvalidParameterException("Email must be not blank");
        }

        if (validateEmailIsExists(request.email())) {
            throw new ConflictException("Email already exists.");
        }

        User user = mapper.toEntity(request);
        user.setRole(Role.EMPLOYEE);

        User savedUser = repository.save(user);

        log.info("[{}] [SaveUser] Recebido dados do usuário {}", className, savedUser.getId());

        return mapper.toDto(savedUser);
    }

    public boolean validateEmailIsExists(String email){
        return repository.findUserByEmail(email).isPresent();
    }
}
