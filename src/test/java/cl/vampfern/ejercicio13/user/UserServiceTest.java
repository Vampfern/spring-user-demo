package cl.vampfern.ejercicio13.user;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @Test
    public void whenFindById_thenReturnUser() {
        UserEntity mockedUserEntity = new UserEntity();
        mockedUserEntity.setId(UUID.randomUUID());

        when(userRepository.findById(any(UUID.class))).then((Answer<Optional<UserEntity>>) invocation -> Optional.of(mockedUserEntity));

        UserEntity userEntity = userService.getUser(UUID.randomUUID());

        Assertions.assertNotNull(userEntity);
        Assertions.assertSame(mockedUserEntity.getId(), userEntity.getId());
    }

}
