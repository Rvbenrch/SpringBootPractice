package com.novaBankpractice.dto;
import lombok.Data;

@Data // Genera getters, setters, toString y constructores en segundo plano
public class OperacionDTO {
    private Long cuentaId;
    private Double cantidad;
}