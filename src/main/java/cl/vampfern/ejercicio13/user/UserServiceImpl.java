package cl.vampfern.ejercicio13.user;

import cl.vampfern.ejercicio13.exception.InvalidDataException;
import cl.vampfern.ejercicio13.exception.MissingParamException;
import cl.vampfern.ejercicio13.exception.NotFoundException;
import cl.vampfern.ejercicio13.user.dto.CreateUserDto;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public UserEntity createUser(CreateUserDto createUserDto) {
        LOGGER.info("Creating user {}", createUserDto);

        UserEntity found = this.userRepository.findUserEntityByEmail(createUserDto.getEmail());
        if (Objects.nonNull(found)) {
            throw new InvalidDataException("El correo ya registrado");
        }

        UserEntity userEntity = userMapper.convertToEntity(createUserDto, true);

        if (Objects.nonNull(userEntity)) {
            return this.userRepository.save(userEntity);
        }

        return null;
    }

    @Override
    public UserEntity getUser(UUID id) {
        return this.userRepository.findById(id).orElseThrow(NotFoundException::new);
    }

    @Override
    public UserEntity getUser(String id) {
        if (StringUtils.isEmpty(id)) {
            throw new MissingParamException("Missing param id");
        }

        return this.getUser(UUID.fromString(id));
    }

    @Override
    public UserEntity updateUser(CreateUserDto createUserDto, UUID id) {
        // Throw not found error
        this.getUser(id);

        UserEntity userEntity = userMapper.convertToEntity(createUserDto, false);
        userEntity.setId(id);

        return this.userRepository.save(userEntity);
    }

    @Override
    public UserEntity partialUpdate(CreateUserDto createUserDto, UUID id){
        UserEntity userEntity = this.getUser(id);
        userMapper.mergeEntities(userMapper.convertToEntity(createUserDto, false), userEntity);
        return this.userRepository.save(userEntity);
    }

    @Override
    public UserEntity deleteUser(UUID id) {
        UserEntity found = this.getUser(id);
        this.userRepository.deleteById(id);

        return found;
    }
}
