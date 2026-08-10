package com.viratech.supportdesk.service;

import com.viratech.supportdesk.domain.User;
import com.viratech.supportdesk.dto.UserRequest;
import com.viratech.supportdesk.dto.UserResponse;
import com.viratech.supportdesk.enums.Role;
import com.viratech.supportdesk.mapper.UserMapper;
import com.viratech.supportdesk.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.context.annotation.Description;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class UserServiceTest {

    @Mock
    private UserRepository repository;

    @InjectMocks
    private UserService service;

    @BeforeEach
    void setUp(){
        repository = mock(UserRepository.class);
        UserMapper mapper = new UserMapper();
        service = new UserService(repository, mapper);
    }

    @Test
    @DisplayName("Deve criar um usuário com a role EMPLOYEE")
    void shouldSaveUserSuccessfully(){
        UserRequest request = new UserRequest(
                "junior",
                "junior@email.com"
        );

        when(repository.save(any(User.class)))
                .thenAnswer(invocation -> invocation.getArgument(0, User.class));

        UserResponse response = service.saveUser(request);

        assertNotNull(response);
        assertEquals("junior", response.name());
        assertEquals("junior@email.com", response.email());
        assertEquals(Role.EMPLOYEE, response.role());

        verify(repository, times(1))
                .save(any(User.class));
    }
}
