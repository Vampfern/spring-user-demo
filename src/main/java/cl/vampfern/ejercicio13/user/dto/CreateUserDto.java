package cl.vampfern.ejercicio13.user.dto;

import cl.vampfern.ejercicio13.phone.dto.CreateUserPhoneDto;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.Set;

@Data
@Builder
public class CreateUserDto {

    @NotEmpty
    private String name;

    @Email(regexp = ".+@.+\\..+")
    @NotEmpty
    private String email;

    @NotEmpty
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{3,}$")
    private String password;

    private Set<CreateUserPhoneDto> phones;

    private Boolean isActive;

}
