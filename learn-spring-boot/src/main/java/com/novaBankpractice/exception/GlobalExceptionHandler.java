package com.novaBankpractice.exception;

import com.novaBankpractice.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponse> handleRuntimeException(RuntimeException ex) {

        // Creamos nuestro objeto de error usando el constructor generado por Lombok
        ErrorResponse error = new ErrorResponse(
                ex.getMessage(), // Aquí irá el texto "Saldo insuficiente."
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value() // Devuelve un código 400
        );

        // Lo enviamos a Postman en formato JSON
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}