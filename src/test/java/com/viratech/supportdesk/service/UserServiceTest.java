package com.viratech.supportdesk.service;

import com.viratech.supportdesk.builders.UserBuilder;
import com.viratech.supportdesk.domain.User;
import com.viratech.supportdesk.dto.UserRequest;
import com.viratech.supportdesk.dto.UserResponse;
import com.viratech.supportdesk.enums.Role;
import com.viratech.supportdesk.exceptions.ConflictException;
import com.viratech.supportdesk.exceptions.InvalidParameterException;
import com.viratech.supportdesk.mapper.UserMapper;
import com.viratech.supportdesk.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.stream.Stream;

import static com.viratech.supportdesk.builders.UserBuilder.aUser;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository repository;

    @Mock
    private UserMapper mapper;

    @InjectMocks
    private UserService service;

    static UserRequest user1 = new UserRequest("", "user@email");
    static UserRequest user2 = new UserRequest("User2", "");

    static Stream<Arguments> getParams(){
        return Stream.of(
                Arguments.of(user1, "Name must be not blank"),
                Arguments.of(user2, "Email must be not blank")
        );
    }

    @Test
    @DisplayName("Deve criar um usuário com Sucesso e com a Role EMPLOYEE")
    void shouldSaveUserSuccessfully(){

        User userEntity = UserBuilder.aUser().now();

        UserRequest request = new UserRequest(
                "Usuario Valido",
                "email@email"
        );

        UserResponse response = new UserResponse(
             userEntity.getId(),
             userEntity.getName(),
             userEntity.getEmail(),
             userEntity.getRole(),
             userEntity.getCreatedAt()
        );

        when(mapper.toEntity(request)).thenReturn(userEntity);
        when(repository.save(userEntity)).thenReturn(userEntity);
        when(mapper.toDto(userEntity)).thenReturn(response);

        UserResponse result = service.saveUser(request);

        assertNotNull(result.id());
        assertEquals(response, result);
        assertEquals(Role.EMPLOYEE, result.role());
    }

    @ParameterizedTest(name="{1} : {0}")
    @MethodSource("getParams")
    @DisplayName("Deve lançar exceção caso campo esteja vazio")
    public void shouldExceptionOnFieldNullOrEmpty(UserRequest user, String mensagem){

        Exception exception = assertThrows(InvalidParameterException.class,
                ()-> service.saveUser(user));
        assertEquals(mensagem, exception.getMessage());
    }

    @Test
    @DisplayName("Deve lançar exceção quando salvar usuário com email que já existe")
    public void shouldThrowExceptionWhenAlreadyExists(){

        UserRequest request = new UserRequest(
                "Usuario Valido",
                "email@gmail.com"
        );

        when(repository.emailExists(request.email())).thenReturn(true);

        ConflictException ex = assertThrows(ConflictException.class,
                ()-> service.saveUser(request));

        assertEquals("Email already exists.", ex.getMessage());
    }

}
