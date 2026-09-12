package com.viratech.supportdesk.controller;

import com.viratech.supportdesk.dto.UserRequest;
import com.viratech.supportdesk.dto.UserResponse;
import com.viratech.supportdesk.enums.Role;
import com.viratech.supportdesk.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import tools.jackson.databind.ObjectMapper;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    @Mock
    private UserService service;

    @InjectMocks
    private UserController controller;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp(){
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

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

    @Test
    @DisplayName("Deve salvar Usuário usando requisição HTTP")
    public void shouldSaveUserThroughHttp() throws Exception {

        String request = """
                {
                    "name":"Usuario Valido",
                    "email":"email@gmail.com"
                }
                """;

        mockMvc.perform(post("/api/v1/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(request))
                .andExpect(status().isCreated());
    }
}
