package cl.vampfern.ejercicio13.user;

import cl.vampfern.ejercicio13.user.dto.CreateUserDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Component
public class UserMapper {

    private final ModelMapper modelMapper;

    public UserMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public UserEntity convertToEntity(CreateUserDto createUserDto, boolean isNewRecord) {
        if (Objects.isNull(createUserDto)) {
            return null;
        }

        UserEntity userEntity =  modelMapper.map(createUserDto, UserEntity.class);

        if (isNewRecord) {
            LocalDateTime now = LocalDateTime.now();
            userEntity.setLastLogin(now);
            userEntity.setToken(UUID.randomUUID());
            userEntity.setIsActive(true);
        }

        return userEntity;
    }

    public UserEntity mergeEntities(UserEntity user, UserEntity user2) {
        modelMapper.getConfiguration().setSkipNullEnabled(true);
        modelMapper.map(user, user2);
        return user2;
    }

}
