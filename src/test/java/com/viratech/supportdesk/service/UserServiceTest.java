package com.viratech.supportdesk.service;

import com.viratech.supportdesk.domain.User;
import com.viratech.supportdesk.dto.UserRequest;
import com.viratech.supportdesk.dto.UserResponse;
import com.viratech.supportdesk.enums.Role;
import com.viratech.supportdesk.exceptions.InvalidParameterException;
import com.viratech.supportdesk.mapper.UserMapper;
import com.viratech.supportdesk.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository repository;

    private UserMapper mapper;
    private UserService service;

    @BeforeEach
    void setUp(){
        mapper = new UserMapper();
        service = new UserService(repository, mapper);
    }

    static UserRequest user1 = new UserRequest("", "user@email");
    static UserRequest user2 = new UserRequest("User2", "");

    static Stream<Arguments> getParams(){
        return Stream.of(
                Arguments.of(user1, "Name must be not blank"),
                Arguments.of(user2, "Email must be not blank")
        );
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

    @ParameterizedTest(name="{1} : {0}")
    @MethodSource("getParams")
    @DisplayName("Deve lançar exceção caso campo esteja vazio")
    public void shouldExceptionOnFieldNullOrEmpty(UserRequest user, String mensagem){

        Exception exception = assertThrows(InvalidParameterException.class,
                ()-> service.saveUser(user));
        assertEquals(mensagem, exception.getMessage());
    }
}
