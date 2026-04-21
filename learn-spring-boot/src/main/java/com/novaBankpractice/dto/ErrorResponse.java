package com.novaBankpractice.dto;

import java.time.LocalDateTime;

public class ErrorResponse {
    private String mensaje;
    private LocalDateTime timestamp;
    private int status;

    public ErrorResponse(String mensaje, int status) {
        this.mensaje = mensaje;
        this.status = status;
        this.timestamp = LocalDateTime.now();
    }
    // Getters
    public String getMensaje() { return mensaje; }
    public LocalDateTime getTimestamp() { return timestamp; }
    public int getStatus() { return status; }
}