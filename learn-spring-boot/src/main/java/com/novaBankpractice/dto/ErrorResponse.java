package com.novaBankpractice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data // Genera getters, setters, toString
@AllArgsConstructor // Genera un constructor con todos los argumentos
@NoArgsConstructor // Genera un constructor vacío
public class ErrorResponse {
    private String mensaje;
    private LocalDateTime timestamp;
    private int status;
}