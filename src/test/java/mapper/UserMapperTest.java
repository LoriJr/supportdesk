package mapper;

import com.viratech.supportdesk.domain.User;
import com.viratech.supportdesk.dto.UserRequest;
import com.viratech.supportdesk.dto.UserResponse;
import com.viratech.supportdesk.enums.Role;
import com.viratech.supportdesk.mapper.UserMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.viratech.supportdesk.builders.UserBuilder.aUser;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserMapperTest {

    @Mock
    private UserMapper mapper;

    @Test
    @DisplayName("Deve converter dto para Entidade de Usuário")
    public void shouldConvertDtoToEntityUser(){

        UserRequest request = new UserRequest(
                "Usuario Valido",
                "email@gmail"
        );

        User user = aUser().now();

        when(mapper.toEntity(request)).thenReturn(user);

        User result = mapper.toEntity(request);

        assertEquals(Role.EMPLOYEE, result.getRole());
    }

    @Test
    @DisplayName("Deve converter Entidade de Usuário para dto")
    public void shouldConvertEntityUserToDto(){

        User user = aUser().now();
        UserResponse response = new UserResponse(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getRole(),
                user.getCreatedAt()
        );

        when(mapper.toDto(user)).thenReturn(response);

        UserResponse result = mapper.toDto(user);

        assertEquals("Usuario Valido", result.name());
    }
}
