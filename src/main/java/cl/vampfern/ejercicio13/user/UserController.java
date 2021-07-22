package cl.vampfern.ejercicio13.user;

import cl.vampfern.ejercicio13.exception.ValidationException;
import cl.vampfern.ejercicio13.user.dto.CreateUserDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserEntity createUser(@RequestBody @Validated CreateUserDto createUserDto, BindingResult errors) {
        if (errors.hasErrors()) {
            throw new ValidationException(errors);
        }

        return this.userService.createUser(createUserDto);
    }

    @GetMapping("/{id}")
    public UserEntity getUser(@PathVariable(value = "id", required = false) String id) {
        LOGGER.info("Received get user id {}", id);
        return this.userService.getUser(UUID.fromString(id));
    }

    @DeleteMapping("/{id}")
    public UserEntity deleteUser(@PathVariable("id") UUID id) {
        return this.userService.deleteUser(id);
    }

    @PutMapping("/{id}")
    public UserEntity updateUser(@RequestBody CreateUserDto createUserDto, @PathVariable("id") UUID id) {
        return this.userService.updateUser(createUserDto, id);
    }

    @PatchMapping("/{id}")
    public UserEntity partialUpdateUser(@RequestBody CreateUserDto createUserDto, @PathVariable("id") UUID id) {
        return this.userService.partialUpdate(createUserDto, id);
    }
}
