package cl.vampfern.ejercicio13.exception;

public class NotFoundException extends RuntimeException {

    public NotFoundException() {
        super("No encontrado");
    }

    public NotFoundException(String message) {
        super(message);
    }

}
