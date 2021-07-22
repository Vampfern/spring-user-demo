package cl.vampfern.ejercicio13.user;

import cl.vampfern.ejercicio13.Ejercicio13Application;
import cl.vampfern.ejercicio13.exception.NotFoundException;
import cl.vampfern.ejercicio13.exception.ValidationException;
import cl.vampfern.ejercicio13.user.dto.CreateUserDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = Ejercicio13Application.class)
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    private final String apiUrl = "/users";

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void whenCreateUser_thenReturnOk() throws Exception {
        CreateUserDto createUserDto = CreateUserDto.builder()
                .email("some@mail.com")
                .name("some name")
                .password("SomePass123")
                .build();

        String json = objectMapper.writeValueAsString(createUserDto);

        when(userService.createUser(any(CreateUserDto.class))).thenReturn(new UserEntity());

        mockMvc.perform(
                post(this.apiUrl)
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("utf-8")
                        .content(json))
                .andExpect(status().isCreated());
    }

    @Test
    public void whenCreateUserWithInvalidData_thenThrowException() throws Exception {
        CreateUserDto createUserDto = CreateUserDto.builder()
                .email("novalid@mail")
                .name("some name")
                .password("passWithoutNumberNotValid")
                .build();

        String json = objectMapper.writeValueAsString(createUserDto);

        when(userService.createUser(any(CreateUserDto.class))).thenReturn(new UserEntity());

        mockMvc.perform(
                post(this.apiUrl)
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("utf-8")
                        .content(json))
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof ValidationException));
    }

    @Test
    public void whenDeleteUser_thenReturnOk() throws Exception {
        when(userService.deleteUser(any(UUID.class))).thenReturn(new UserEntity());

        mockMvc.perform(
                delete(this.apiUrl + "/" + UUID.randomUUID()))
                .andExpect(status().isOk());
    }

    @Test
    public void whenDeleteUserThatDoesNotExist_thenThrowException() throws Exception {
        when(userService.deleteUser(any(UUID.class))).thenThrow(new NotFoundException("User not found"));

        mockMvc.perform(
                delete(this.apiUrl + "/" + UUID.randomUUID()))
                .andExpect(status().isNotFound())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof NotFoundException));
    }

    @Test
    public void whenGetUser_thenReturnOk() throws Exception {
        when(userService.getUser(any(UUID.class))).thenReturn(new UserEntity());

        mockMvc.perform(
                get(this.apiUrl + "/" + UUID.randomUUID())
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("utf-8"))
                .andExpect(status().isOk());
    }

    @Test
    public void whenGetNotExistingUser_thenThrowNotFoundException() throws Exception {
        when(userService.getUser(any(UUID.class))).thenThrow(new NotFoundException("User not found"));

        mockMvc.perform(
                get(this.apiUrl + "/" + UUID.randomUUID())
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("utf-8"))
                .andExpect(status().isNotFound())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof NotFoundException));
    }
}
