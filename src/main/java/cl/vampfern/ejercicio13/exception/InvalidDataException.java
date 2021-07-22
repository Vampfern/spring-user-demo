package cl.vampfern.ejercicio13.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class InvalidDataException extends RuntimeException {

    public InvalidDataException(String message) {
        super(message);
    }

}
