package com.viratech.supportdesk.repository;

import com.viratech.supportdesk.domain.User;
import com.viratech.supportdesk.enums.Role;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

// Ativa o profile "test" e, por isso, utiliza o application-test.properties
@ActiveProfiles("test")

// Carrega o contexto necessário para testar a camada JPA, incluindo entidades e repositórios
@DataJpaTest

// Mantém o PostgreSQL configurado em vez de substituí-lo por um banco embarcado
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRepositoryTest {

    @Autowired
    private UserRepository repository;

    @Test
    @DisplayName("Deve salvar usuário")
    void shouldSaveUser(){

        User user = User.builder()
                .name("junior")
                .email("junior@gmail.com")
                .role(Role.EMPLOYEE)
                .createdAt(LocalDateTime.now())
                .build();

        User savedUser = repository.save(user);

        assertNotNull(savedUser.getId());
        assertEquals("junior", savedUser.getName());
        assertEquals("junior@gmail.com", savedUser.getEmail());
        assertEquals(Role.EMPLOYEE, savedUser.getRole());
    }
}
