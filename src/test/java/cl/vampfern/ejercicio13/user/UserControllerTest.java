package cl.vampfern.ejercicio13.user;

import cl.vampfern.ejercicio13.Ejercicio13Application;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest(classes = Ejercicio13Application.class)
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    public void whenCreateUser_thenReturnOk() {

    }

    @Test
    public void whenCreateUserWithInvalidData_thenThrowException() {

    }

    @Test
    public void whenDeleteUser_thenReturnOk() {

    }

    @Test
    public void whenDeleteUserThatDoesNotExist_thenThrowException() {

    }

}
