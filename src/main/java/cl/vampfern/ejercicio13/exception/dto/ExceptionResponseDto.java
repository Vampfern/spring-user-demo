package cl.vampfern.ejercicio13.exception.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ExceptionResponseDto {

    private String mensaje;

}
