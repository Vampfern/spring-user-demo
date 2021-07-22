package cl.vampfern.ejercicio13.exception;

import cl.vampfern.ejercicio13.exception.dto.ExceptionResponseDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(RestResponseEntityExceptionHandler.class);

    @ExceptionHandler(value = { MissingParamException.class })
    protected ResponseEntity<Object> handleMissingParam(RuntimeException ex, WebRequest request) {
        LOGGER.error("Handle missing param exception {}", ex.getLocalizedMessage());

        return handleExceptionInternal(ex,
                ExceptionResponseDto.builder().mensaje(ex.getLocalizedMessage()).build(),
                new HttpHeaders(),
                HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(value = { NotFoundException.class })
    protected ResponseEntity<Object> handleNotFound(RuntimeException ex, WebRequest request) {
        LOGGER.error("Handle not found exception {}", ex.getLocalizedMessage());

        return handleExceptionInternal(ex,
                ExceptionResponseDto.builder().mensaje(ex.getLocalizedMessage()).build(),
                new HttpHeaders(),
                HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(value = { ValidationException.class })
    protected ResponseEntity<Object> handleValidation(RuntimeException ex, WebRequest request) {
        LOGGER.error("Handle validation exception {}", ex.getLocalizedMessage());

        return handleExceptionInternal(ex,
                ExceptionResponseDto.builder().mensaje(ex.getLocalizedMessage()).build(),
                new HttpHeaders(),
                HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(value = { Exception.class })
    protected ResponseEntity<Object> handleException(RuntimeException ex, WebRequest request) {
        LOGGER.error("Handle exception {}", ex.getLocalizedMessage());

        return handleExceptionInternal(ex,
                ExceptionResponseDto.builder().mensaje(ex.getLocalizedMessage()).build(),
                new HttpHeaders(),
                HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

    @Override
    protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        LOGGER.error("Handle type mismatch exception {}", ex.getLocalizedMessage());

        return handleExceptionInternal(ex,
                ExceptionResponseDto.builder().mensaje(ex.getLocalizedMessage()).build(),
                headers,
                status,
                request);
    }

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        LOGGER.error("Handle http request method not supported exception {}", ex.getLocalizedMessage());

        return handleExceptionInternal(ex,
                ExceptionResponseDto.builder().mensaje(ex.getLocalizedMessage()).build(),
                headers,
                HttpStatus.INTERNAL_SERVER_ERROR, request);
    }
}
