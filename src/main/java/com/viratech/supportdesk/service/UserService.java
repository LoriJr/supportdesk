package com.viratech.supportdesk.service;

import com.viratech.supportdesk.domain.User;
import com.viratech.supportdesk.enums.Role;
import com.viratech.supportdesk.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;
    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    public User saveUser(User user){

        String className = UserService.class.getSimpleName();

        if(user == null){
            throw new IllegalStateException("Request body must not be null");
        }

        user.setRole(Role.EMPLOYEE);

        log.info("[{}] [UserSave] Recebido dados do usuário {}", className, user);

        return repository.save(user);
    }
}
