package com.viratech.supportdesk.controller;

import com.viratech.supportdesk.dto.UserRequest;
import com.viratech.supportdesk.dto.UserResponse;
import com.viratech.supportdesk.enums.Role;
import com.viratech.supportdesk.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    @Mock
    private UserService service;

    @InjectMocks
    private UserController controller;

    @Test
    @DisplayName("Deve salvar usuário e retornar 201")
    void shoudSaveUserAndReturn201(){

        UserRequest request = new UserRequest(
                "junior",
                "junior@gmail.com");

        UserResponse response = new UserResponse(
               1L,
               "junior",
               "junior@gmail.com",
                Role.EMPLOYEE,
                LocalDateTime.now()
        );

        when(service.saveUser(request))
                .thenReturn(response);

        ResponseEntity<UserResponse> result =
                controller.save(request);

        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        assertEquals(response, result.getBody());

        verify(service).saveUser(request);
    }
}
