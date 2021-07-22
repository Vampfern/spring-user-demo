package cl.vampfern.ejercicio13.user;

import cl.vampfern.ejercicio13.user.dto.CreateUserDto;

import java.util.UUID;

public interface UserService {

    UserEntity createUser(CreateUserDto createUserDto);

    UserEntity getUser(UUID id);

    UserEntity getUser(String id);

    UserEntity updateUser(CreateUserDto createUserDto, UUID id);

    UserEntity partialUpdate(CreateUserDto createUserDto, UUID id);

    UserEntity deleteUser(UUID id);

}
