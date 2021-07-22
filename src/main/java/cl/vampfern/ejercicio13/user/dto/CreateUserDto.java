package cl.vampfern.ejercicio13.user.dto;

import cl.vampfern.ejercicio13.phone.dto.CreateUserPhoneDto;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Data
public class CreateUserDto {

    @NotEmpty
    private String name;

    @Email(regexp = ".+@.+\\..+")
    @NotEmpty
    private String email;

    @NotEmpty
    private String password;

    private Set<CreateUserPhoneDto> phones;

    private Boolean isActive;

}
