package cl.vampfern.ejercicio13.user;

import cl.vampfern.ejercicio13.exception.NotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    @Test
    public void whenFindById_thenReturnUser() {
        UserEntity mockedUserEntity = new UserEntity();
        mockedUserEntity.setId(UUID.randomUUID());

        when(userRepository.findById(any(UUID.class))).then((Answer<Optional<UserEntity>>) invocation -> Optional.of(mockedUserEntity));

        UserEntity userEntity = userService.getUser(UUID.randomUUID());

        Assertions.assertNotNull(userEntity);
        Assertions.assertSame(mockedUserEntity.getId(), userEntity.getId());
    }

    @Test
    public void whenFindByIdNotExistingUser_thenThrowNotFoundException() {
        when(userRepository.findById(any(UUID.class))).then((Answer<Optional<UserEntity>>) invocation -> Optional.empty());

        Exception exception = assertThrows(NotFoundException.class, () -> {
            userService.getUser(UUID.randomUUID());
        });

        assertTrue(exception.getMessage().contains("No encontrado"));
    }

    @Test
    public void whenDeleteUser_thenDeleteAndReturnDeletedUser() {
        UserEntity mockedUserEntity = new UserEntity();
        mockedUserEntity.setId(UUID.randomUUID());

        when(userRepository.findById(any(UUID.class))).then((Answer<Optional<UserEntity>>) invocation -> Optional.of(mockedUserEntity));

        UserEntity userEntity = userService.deleteUser(UUID.randomUUID());

        Assertions.assertNotNull(userEntity);
        Assertions.assertSame(mockedUserEntity.getId(), userEntity.getId());
        verify(userRepository, times(1)).deleteById(any(UUID.class));
    }

    @Test
    public void whenDeleteNonExistentUser_thenThrowNotFoundException() {
        UserEntity mockedUserEntity = new UserEntity();
        mockedUserEntity.setId(UUID.randomUUID());

        when(userRepository.findById(any(UUID.class))).then((Answer<Optional<UserEntity>>) invocation -> Optional.empty());

        Exception exception = assertThrows(NotFoundException.class, () -> {
            userService.deleteUser(UUID.randomUUID());
        });

        verify(userRepository, times(0)).deleteById(any(UUID.class));
        assertTrue(exception.getMessage().contains("No encontrado"));
    }

}
