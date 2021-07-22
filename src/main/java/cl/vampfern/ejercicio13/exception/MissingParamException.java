package cl.vampfern.ejercicio13.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class MissingParamException extends RuntimeException {

    public MissingParamException(String message) {
        super(message);
    }

}
